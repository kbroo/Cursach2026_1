package by.vsu.dao;

import by.vsu.model.CartItem;

public interface CartDAO {
    int save(CartItem cartItem);
    void increateCartItem(int userId, int productId, int quantity);
    CartItem getCartItemById(int id);
    CartItem getCartItemByUserAndProduct(int userId, int productId);
}
