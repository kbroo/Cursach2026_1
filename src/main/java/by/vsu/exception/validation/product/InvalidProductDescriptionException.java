package by.vsu.exception.validation.product;

import by.vsu.exception.validation.ValidationException;

public class InvalidProductDescriptionException extends ValidationException {
    public InvalidProductDescriptionException(String message) {
        super(message);
    }
}
