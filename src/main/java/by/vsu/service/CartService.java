package by.vsu.service;

import by.vsu.dao.CartItemDAO;
import by.vsu.dao.impl.CartItemDAOImpl;
import by.vsu.model.CartItem;

public class CartItemService {
    private final CartItemDAO cartItemDAO = new CartItemDAOImpl();

    public CartItem createCartItem(int userId, int productId, int quantity) {
        //
    }
}
