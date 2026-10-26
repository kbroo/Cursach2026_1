package by.vsu.dao;

import by.vsu.model.CartItem;

import java.util.List;

public interface CartDAO {
    int save(CartItem cartItem);
    void delete(int cartItemId);
    CartItem getCartItemByUserAndProduct(int userId, int productId);
    List<CartItem> getCartItemsByUser(int userId);
    void updateQuantity(int id, int quantity);
}
