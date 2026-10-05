package by.vsu.service;

import by.vsu.dao.CartDAO;
import by.vsu.dao.ProductDAO;
import by.vsu.dao.impl.CartDAOImpl;
import by.vsu.dao.impl.ProductDAOImpl;
import by.vsu.exception.ServiceException;
import by.vsu.model.CartItem;

public class CartService {
    private final CartDAO cartItemDAO = new CartDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    public CartItem addToCart(int userId, int productId, int quantity) {
        if (quantity < 1) {
            throw new ServiceException("Невозможно добавить 0 товаров в корзину");
        }
        int stock = productDAO.getStock(productId);
        if (stock <= quantity) {
            throw new ServiceException("На складе недостаточно товара");
        }
        if (cartItemDAO.getCartItemByUserAndProduct(userId, productId) == null) {
            CartItem cartItem = new CartItem(userId, productId, quantity);
            cartItemDAO.save(cartItem);
            return cartItem;
        } else {
            cartItemDAO.increateCartItem(userId, productId, quantity);
            return cartItemDAO.getCartItemByUserAndProduct(userId, productId);
        }
    }
}
