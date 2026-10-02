package by.vsu.validator;

import by.vsu.exception.InvalidEmailException;
import by.vsu.exception.InvalidNameException;
import by.vsu.exception.InvalidPasswordException;

public class UserValidator {
    public static void validateRegistration(String userName, String userEmail, String password) {
        validateUserName(userName);
        validateEmail(userEmail);
        validatePassword(password);
    }

    public static void validateUserName(String userName) {
        if (userName == null || userName.isBlank()) {
            throw new InvalidNameException("Имя пользователя не может быть пустым");
        }
        if (userName.length() < 2) {
            throw new InvalidNameException("Минимальная длина имени: 2");
        }
        if (!userName.matches("[a-zA-Z0-9_]+")) {
            throw new InvalidNameException("Имя пользователя может состоять только из букв, цифр и нижнего подчеркивания");
        }
    }
    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("Почта не может быть пустой");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailException("Передана некорректная почта");
        }
    }

    public static void validatePassword(String password) {
        if (password == null ||password.isBlank() || password.length() < 8) {
            throw new InvalidPasswordException("Пароль должен быть не короче 8 символов");
        }
        if (!password.matches("^(?=.*\\d)(?=.*[A-Z]).+$")) {
            throw new InvalidPasswordException("Пароль должен содержать хотя бы одну цифру и заглавную букву");
        }
    }
}
