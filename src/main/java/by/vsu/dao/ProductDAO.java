package by.vsu.dao;

import by.vsu.model.Product;
import by.vsu.model.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {
    int save(Product product);
    List<Product> getProducts();
    List<Product> getProducts(int value);
    Product getById(int id);
    Product getByName(String name);
    int getStock(int id);
    void updateStock(int id, int value);
}
