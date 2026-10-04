package by.vsu.dao;

import by.vsu.model.enums.Role;
import by.vsu.model.User;

import java.util.List;

public interface UserDAO {
    int save(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByName(String username);
    User getUserByEmail(String email);
}
