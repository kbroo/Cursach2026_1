package by.vsu.dao.impl;

import by.vsu.dao.OrderDAO;
import by.vsu.exception.DAOException;
import by.vsu.model.CartItem;
import by.vsu.model.Order;
import by.vsu.model.OrderItem;
import by.vsu.model.enums.OrderStatus;
import by.vsu.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public int save(Order order, List<CartItem> items) {
        String sqlInsertIntoOrders = "INSERT INTO orders (userId, totalPrice, address, status, createdAt) VALUES (?, ?, ?, ?, ?)";
        String sqlInsertIntoOrderItems = "INSERT INTO orderItems (orderId, productId, quantity, priceAtMoment) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";
        String sqlDeleteCartItems = "DELETE FROM cartItems WHERE userId = ?";
        int id = -1;
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(sqlInsertIntoOrders, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, order.getUserId());
                    ps.setBigDecimal(2, order.getTotalPrice());
                    ps.setString(3, order.getAddress());
                    ps.setString(4, OrderStatus.NEW.name());
                    ps.setTimestamp(5, Timestamp.valueOf(order.getCreatedAt()));
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) id = rs.getInt(1);
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlInsertIntoOrderItems)) {
                    for (CartItem item : items) {
                        ps.setInt(1, id);
                        ps.setInt(2, item.getProductId());
                        ps.setInt(3, item.getQuantity());
                        ps.setBigDecimal(4, item.getProductPrice());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlUpdateStock)) {
                    for (CartItem item : items) {
                        ps.setInt(1, item.getQuantity());
                        ps.setInt(2, item.getProductId());
                        ps.setInt(3, item.getQuantity());
                        int update = ps.executeUpdate();
                        if (update == 0) {
                            throw new DAOException("Товара с id " + item.getProductId() + " недостаточно на складе.");
                        }
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlDeleteCartItems)) {
                    ps.setInt(1, order.getUserId());
                    ps.executeUpdate();
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw new DAOException("Ошибка при оформлении заказа.", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка оформления заказа.", e);
        }
        return id;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "SELECT id, userId, totalPrice, address, status, createdAt FROM orders WHERE userId = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(orderMapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения заказов пользователя", e);
        }
        return orders;
    }

    @Override
    public List<OrderItem> getOrderItems(int id) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT id, orderId, productId, quantity, priceAtMoment FROM orderItems WHERE orderId = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(orderItemMapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения списка товаров заказа с id " + id, e);
        }
        return items;
    }

    private Order orderMapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int userId = rs.getInt("userId");
        BigDecimal totalPrice = rs.getBigDecimal("totalPrice");
        String address = rs.getString("address");
        OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
        Timestamp timestamp = rs.getTimestamp("createdAt");
        LocalDateTime createdAt = timestamp.toLocalDateTime();
        return new Order(id, userId, totalPrice, address, status, createdAt);
    }

    private OrderItem orderItemMapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int orderId = rs.getInt("orderId");
        int productId = rs.getInt("productId");
        int quantity = rs.getInt("quantity");
        BigDecimal priceAtMoment = rs.getBigDecimal("priceAtMoment");
        return new OrderItem(id, orderId, productId, quantity, priceAtMoment);
    }
}
