package by.vsu.validator;

import by.vsu.exception.validation.ProductValidationException;
import by.vsu.model.enums.Category;

import java.math.BigDecimal;

public class ProductValidator {
    public static void validateProduct(String name, String description, Category category,
                                       String imageUrl, BigDecimal price, int stock) {
        validateName(name);
        validateDescription(description);
        validateCategory(category);
        validateImageUrl(imageUrl);
        validatePrice(price);
        validateStock(stock);
    }

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ProductValidationException("Название товара не может быть пустым");
        }
        if (name.length() < 2 || name.length() > 200) {
            throw new ProductValidationException("Название должно быть от 2 до 200 символов");
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new ProductValidationException("Описание товара не может быть пустым");
        }
        if (description.length() < 10) {
            throw new ProductValidationException("Описание должно иметь не менее 10 символов");
        }
        if (description.length() > 2000) {
            throw new ProductValidationException("Описание должно иметь не более 2000 символов");
        }
    }

    public static void validateCategory(Category category) {
        if (category == null) {
            throw new ProductValidationException("Категория не выбрана");
        }
    }

    public static void validateImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new ProductValidationException("Путь изображение не может быть пустым");
        }
        if (imageUrl.length() > 255) {
            throw new ProductValidationException("Передан слишком длинный путь к изображению");
        }
        if (!imageUrl.startsWith("/") && !imageUrl.startsWith("http")) {
            throw new ProductValidationException("Некорректный путь к изображению");
        }
    }

    public static void validatePrice(BigDecimal price) {
        if (price == null) {
            throw new ProductValidationException("Цена не может быть пустой");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductValidationException("Цена товара не может быть меньше или равна нулю");
        }
        if (price.compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new ProductValidationException("Цена товара не может превышать 99999999.99");
        }
    }

    public static void validateStock(int stock) {
        if (stock < 0) {
            throw new ProductValidationException("Количество товара не может быть меньше 0");
        }
    }
}
