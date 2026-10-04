package by.vsu.exception.validation.product;

import by.vsu.exception.validation.ValidationException;

public class InvalidProductNameException extends ValidationException {
    public InvalidProductNameException(String message) {
        super(message);
    }
}
