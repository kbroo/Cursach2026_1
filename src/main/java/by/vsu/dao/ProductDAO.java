package by.vsu.dao;

import by.vsu.model.Product;
import by.vsu.model.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {
    int save(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product getProductByName(String name);
}
