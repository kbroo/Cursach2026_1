package by.vsu.exception.validation.user;

import by.vsu.exception.validation.ValidationException;

public class InvalidUserNameException extends ValidationException {
    public InvalidUserNameException(String message) {
        super(message);
    }
}
