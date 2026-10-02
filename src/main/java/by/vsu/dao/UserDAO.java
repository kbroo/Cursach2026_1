package by.vsu.dao;

import by.vsu.model.Role;
import by.vsu.model.User;

import java.util.List;

public interface UserDAO {
    void createUser(String userName, String userEmail, String hashPassword, Role userRole);
    List<User> getAllUsers();
    User getUser(int id);
}
