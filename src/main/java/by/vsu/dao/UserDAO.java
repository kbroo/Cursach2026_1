package by.vsu.dao;

import by.vsu.model.enums.Role;
import by.vsu.model.User;

import java.util.List;

public interface UserDAO {
    boolean createUser(String userName, String userEmail, String hashPassword, Role userRole);
    List<User> getAllUsers();
    User getUser(int id);
}
