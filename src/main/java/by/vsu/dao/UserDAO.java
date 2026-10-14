package by.vsu.dao;

import by.vsu.model.enums.Role;
import by.vsu.model.User;

import java.util.List;

public interface UserDAO {
    int save(User user);
    List<User> getAll();
    User getById(int id);
    User getByName(String username);
    User getByEmail(String email);
}
