package by.vsu.exception.validation.product;

import by.vsu.exception.validation.ValidationException;

public class InvalidProductCategoryException extends ValidationException {
    public InvalidProductCategoryException(String message) {
        super(message);
    }
}
