package by.vsu.service;

import by.vsu.dao.ProductDAO;
import by.vsu.dao.impl.ProductDAOImpl;
import by.vsu.exception.DAOException;
import by.vsu.exception.ServiceException;
import by.vsu.exception.validation.ValidationException;
import by.vsu.model.Product;
import by.vsu.model.enums.Category;
import by.vsu.validator.ProductValidator;
import org.eclipse.tags.shaded.org.apache.xpath.res.XPATHErrorResources_ru;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    public Product create(String name, String description, Category category,
                                 String imageUrl, BigDecimal price, int stock) {
        try {
            ProductValidator.validateProduct(name, description, category, imageUrl, price, stock);
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (productDAO.getByName(name) != null) {
            throw new ServiceException("Такой товар уже существует");
        }
        Product product = new Product(name, description, category, imageUrl, price, stock);
        int id = productDAO.save(product);
        product.setId(id);
        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = List.of();
        try {
            products = productDAO.getProducts();
            if (products.isEmpty()) throw new ServiceException("Товаров нет");
        } catch (DAOException e) {
            throw new ServiceException("Ошибка получения товаров");
        }
        return products;
    }

    public List<Product> getProducts(int value) {
        List<Product> products = List.of();
        try {
            products = productDAO.getProducts(value);
            if (products.isEmpty()) throw new ServiceException("Товаров нет");
        } catch (DAOException e) {
            throw new ServiceException("Ошибка получения товаров");
        }
        return products;
    }

    public Product getById(int id) {
        try {
            Product product = productDAO.getById(id);
            if (product == null) throw new ServiceException("Получени пустой товар");
            return product;
        } catch (DAOException e) {
            throw new ServiceException("Ошибка получения товара");
        }
    }
}
