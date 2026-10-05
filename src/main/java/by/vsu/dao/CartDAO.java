package by.vsu.dao;

import by.vsu.model.CartItem;

public interface CartDAO {
    int save(CartItem cartItem);
    CartItem getCartItemByUserAndProduct(int userId, int productId);
    void updateQuantity(int id, int quantity);
}
