package by.vsu.exception.validation.product;

import by.vsu.exception.validation.ValidationException;

public class InvalidProductPriceException extends ValidationException {
    public InvalidProductImageException(String message) {
        super(message);
    }
}
