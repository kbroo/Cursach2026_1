package by.vsu.exception.validation.product;

import by.vsu.exception.validation.ValidationException;

public class InvalidProductStockException extends ValidationException  {
    public InvalidProductStockException(String message) {
        super(message);
    }
}
