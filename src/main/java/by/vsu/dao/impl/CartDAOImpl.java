package by.vsu.dao.impl;

import by.vsu.dao.CartDAO;
import by.vsu.exception.DAOException;
import by.vsu.model.CartItem;
import by.vsu.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;

public class CartDAOImpl implements CartDAO {
    @Override
    public int save(CartItem cartItem) {
        String sql = "INSERT INTO cartItems (userId, productId, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cartItem.getUserId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            throw new DAOException("Ошибка получения id товара добавленного в корзину");
        } catch (SQLException e) {
            throw new DAOException("Ошибка при добавлении товара в корзину", e);
        }
    }

    @Override
    public void increateCartItem(int userId, int productId, int quantity) {
        String sql = "UPDATE cartItems SET quantity = quantity + ? WHERE userId = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Ошибка увеличения кол-ва товара в корзине");
        }
    }

    @Override
    public CartItem getCartItemById(int id) {
        String sql = "SELECT c.id, c.userId, c.productId, c.quantity, " +
                "p.name AS productName, p.price AS productPrice, p.imageUrl " +
                "FROM cartItems c " +
                "JOIN products p ON c.productId = p.id " +
                "WHERE c.id = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения товара корзины по id", e);
        }
        return null;
    }

    @Override
    public CartItem getCartItemByUserAndProduct(int userId, int productId) {
        String sql = "SELECT c.id, c.userId, c.productId, c.quantity, " +
                "p.name AS productName, p.price AS productPrice, p.imageUrl " +
                "FROM cartItems c " +
                "JOIN products p ON c.productId = p.id " +
                "WHERE c.userId = ? AND c.productId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения позиции корзины", e);
        }
        return null;
    }

    private CartItem mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int userId = rs.getInt("userId");
        int productId = rs.getInt("productId");
        int quantity = rs.getInt("quantity");
        String productName = rs.getString("productName");
        BigDecimal productPrice = rs.getBigDecimal("productPrice");
        String imageUrl = rs.getString("imageUrl");
        return new CartItem(id, userId, productId, quantity, productName, productPrice, imageUrl);
    }
}
