package by.vsu.service;

import by.vsu.dao.UserDAO;
import by.vsu.dao.impl.UserDAOImpl;
import by.vsu.exception.validation.ValidationException;
import by.vsu.model.User;
import by.vsu.model.enums.Role;
import by.vsu.util.HashPasswordUtil;
import by.vsu.validator.UserValidator;
import by.vsu.exception.ServiceException;

public class UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    public User register(String userName, String userEmail, String password) {
        try {
            UserValidator.validateRegistration(userName, userEmail, password);
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (userDAO.getUserByName(userName) != null || userDAO.getUserByEmail(userEmail) != null) {
            throw new ServiceException("Пользователь с таким именем или почтой уже существует");
        }
        String hashPassword = HashPasswordUtil.hashPassword(password);
        User user = new User(userName, userEmail, hashPassword, Role.USER);
        int id = userDAO.save(user);
        user.setUserId(id);
        return user;
    }

    public User loginWithUserName(String userName, String password) {
        if (userName == null || userName.isBlank() || password == null || password.isBlank()) {
            throw new ServiceException("Не передано имени или пароля пользователя");
        }
        User user = userDAO.getUserByName(userName);
        if (user == null || !HashPasswordUtil.verifyPassword(user.getHashPassword(), password)) {
            throw new ServiceException("Неверное имя пользователя или пароль");
        }
        return user;
    }

    public User loginWithEmail(String userEmail, String password) {
        if (userEmail == null || userEmail.isBlank() || password == null || password.isBlank()) {
            throw new ServiceException("Не передано почты или пароля пользователя");
        }
        User user = userDAO.getUserByEmail(userEmail);
        if (user == null || !HashPasswordUtil.verifyPassword(user.getHashPassword(), password)) {
            throw new ServiceException("Неверная почта пользователя или пароль");
        }
        return user;
    }
}
