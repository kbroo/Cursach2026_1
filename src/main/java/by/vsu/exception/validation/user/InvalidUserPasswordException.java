package by.vsu.exception.validation.user;

import by.vsu.exception.validation.ValidationException;

public class InvalidUserPasswordException extends ValidationException {
    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
