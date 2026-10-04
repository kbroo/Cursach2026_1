package by.vsu.service;

import by.vsu.dao.ProductDAO;
import by.vsu.dao.impl.ProductDAOImpl;
import by.vsu.exception.ServiceException;
import by.vsu.exception.validation.ValidationException;
import by.vsu.model.Product;
import by.vsu.model.enums.Category;
import by.vsu.validator.ProductValidator;

import java.math.BigDecimal;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    public Product createProduct(String name, String description, Category category,
                                 String imageUrl, BigDecimal price, int stock) {
        try {
            ProductValidator.validateProduct(name, description, category, imageUrl, price, stock);
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (productDAO.getProductByName(name) != null) {
            throw new ServiceException("Такой товар уже существует");
        }
        Product product = new Product(name, description, category, imageUrl, price, stock);
        int id = productDAO.save(product);
        product.setId(id);
        return product;
    }
}
