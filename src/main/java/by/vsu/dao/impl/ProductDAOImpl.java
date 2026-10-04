package by.vsu.dao.impl;

import by.vsu.dao.ProductDAO;
import by.vsu.exception.DAOException;
import by.vsu.model.Product;
import by.vsu.model.enums.Category;
import by.vsu.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int save(String name, String description, Category category,
                              String imageUrl, BigDecimal price, int stock) {
        String sql = "INSERT INTO products (name, description, category, imageUrl, price, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, category.name());
            ps.setString(4, imageUrl);
            ps.setBigDecimal(5, price);
            ps.setInt(6, stock);
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            throw new DAOException("Ошибка получения id созданного товара");
        } catch (SQLException e) {
            throw new DAOException("Ошибка создания товара", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, description, category, imageUrl, price, stock FROM products";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения товаров", e);
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT id, name, description, category, imageUrl, price, stock FROM products WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения товара", e);
        }
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        String sql = "SELECT id, name, description, category, imageUrl, price, stock FROM products WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка получения товара", e);
        }
        return null;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String strCategory = rs.getString("category");
        Category category;
        try {
            category = Category.valueOf(strCategory);
        } catch (IllegalArgumentException e) {
            throw new SQLException("Некорректно переданный в бд аргумент: " + strCategory, e);
        }
        String imageUrl = rs.getString("imageUrl");
        BigDecimal price = rs.getBigDecimal("price");
        int stock = rs.getInt("stock");
        return new Product(id, name, description, category, imageUrl, price, stock);
    }
}
