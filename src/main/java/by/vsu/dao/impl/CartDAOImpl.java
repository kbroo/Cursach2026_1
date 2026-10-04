package by.vsu.dao.impl;

import by.vsu.dao.CartItemDAO;
import by.vsu.exception.DAOException;
import by.vsu.model.CartItem;
import by.vsu.util.DBUtil;

import java.sql.*;

public class CartItemDAOImpl implements CartItemDAO {
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
}
