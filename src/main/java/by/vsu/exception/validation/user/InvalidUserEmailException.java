package by.vsu.exception.validation.user;

import by.vsu.exception.validation.ValidationException;

public class InvalidUserEmailException extends ValidationException {
    public InvalidUserEmailException(String message) {
        super(message);
    }
}
