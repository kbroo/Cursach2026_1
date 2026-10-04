package by.vsu.dao;

import by.vsu.model.Product;
import by.vsu.model.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {
    int save(String name, String description, Category category,
                       String imageUrl, BigDecimal price, int stock);
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product getProductByName(String name);
}
