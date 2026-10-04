package by.vsu.validator;

import by.vsu.exception.validation.user.InvalidUserEmailException;
import by.vsu.exception.validation.user.InvalidUserNameException;
import by.vsu.exception.validation.user.InvalidUserPasswordException;

public class UserValidator {
    public static void validateRegistration(String userName, String userEmail, String password) {
        validateUserName(userName);
        validateEmail(userEmail);
        validatePassword(password);
    }

    public static void validateUserName(String userName) {
        if (userName == null || userName.isBlank()) {
            throw new InvalidUserNameException("Имя пользователя не может быть пустым");
        }
        if (userName.length() < 2) {
            throw new InvalidUserNameException("Минимальная длина имени: 2");
        }
        if (!userName.matches("[a-zA-Z0-9_]+")) {
            throw new InvalidUserNameException("Имя пользователя может состоять только из букв, цифр и нижнего подчеркивания");
        }
    }
    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidUserEmailException("Почта не может быть пустой");
        }
        if (!email.contains("@")) {
            throw new InvalidUserEmailException("Передана некорректная почта");
        }
    }

    public static void validatePassword(String password) {
        if (password == null ||password.isBlank() || password.length() < 8) {
            throw new InvalidUserPasswordException("Пароль должен быть не короче 8 символов");
        }
        if (!password.matches("^(?=.*\\d)(?=.*[A-Z]).+$")) {
            throw new InvalidUserPasswordException("Пароль должен содержать хотя бы одну цифру и заглавную букву");
        }
    }
}
