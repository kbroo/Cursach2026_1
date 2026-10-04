package by.vsu.validator;

import by.vsu.exception.validation.product.*;

import java.math.BigDecimal;

public class ProductValidator {
    public static void validateProduct(String name, String description, String imageUrl,
                                BigDecimal price, int stock) {
        validateName(name);
        validateDescription(description);
        validateImageUrl(imageUrl);
        validatePrice(price);
        validateStock(stock);
    }

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidProductNameException("Название товара не может быть пустым");
        }
        if (name.length() < 2 || name.length() > 200) {
            throw new InvalidProductNameException("Название должно быть от 2 до 200 символов");
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidProductDescriptionException("Описание товара не может быть пустым");
        }
        if (description.length() < 10) {
            throw new InvalidProductDescriptionException("Описание должно иметь не менее 50 символов");
        }
        if (description.length() > 2000) {
            throw new InvalidProductDescriptionException("Описание должно иметь не более 2000 символов");
        }
    }

    public static void validateImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new InvalidProductImageException("Путь изображение не может быть пустым");
        }
        if (imageUrl.length() > 255) {
            throw new InvalidProductImageException("Передан слишком длинный путь к изображению");
        }
        if (!imageUrl.startsWith("/") && !imageUrl.startsWith("http")) {
            throw new InvalidProductImageException("Некорректный путь к изображению");
        }
    }

    public static void validatePrice(BigDecimal price) {
        if (price == null) {
            throw new InvalidProductPriceException("Цена не может быть пустой");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductPriceException("Цена товара не может быть меньше или равна нулю");
        }
        if (price.compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new InvalidProductPriceException("Цена товара не может превышать 99999999.99");
        }
    }

    public static void validateStock(int stock) {
        if (stock < 0) {
            throw new InvalidProductStockException("Количество товара не может быть меньше 0");
        }
    }
}
