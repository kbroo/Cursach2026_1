package by.vsu.dao.impl;

import by.vsu.dao.UserDAO;
import by.vsu.model.enums.Role;
import by.vsu.model.User;
import by.vsu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void createUser(String userName, String userEmail, String hashPassword, Role userRole) {
        String sql = "INSERT INTO users (username, email, hashPassword, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, hashPassword);
            ps.setString(4, userRole.name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, hashPassword, username, email, role FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения пользователей", e);
        }
        return users;
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT id, hashPassword, username, email, role FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения пользователя", e);
        }
        return null;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        int userId = rs.getInt("id");
        String userName = rs.getString("username");
        String userEmail = rs.getString("email");
        String hashPassword = rs.getString("hashPassword");
        Role userRole = Role.valueOf(rs.getString("role"));
        return new User(userId, userName, userEmail, hashPassword, userRole);
    }
}
