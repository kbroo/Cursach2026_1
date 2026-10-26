package by.vsu.service;

import by.vsu.dao.CartDAO;
import by.vsu.dao.ProductDAO;
import by.vsu.dao.impl.CartDAOImpl;
import by.vsu.dao.impl.ProductDAOImpl;
import by.vsu.exception.DAOException;
import by.vsu.exception.ServiceException;
import by.vsu.model.CartItem;

public class CartService {
    private final CartDAO cartItemDAO = new CartDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    public CartItem addToCart(int userId, int productId, int quantity) {
        if (quantity < 1) {
            throw new ServiceException("Невозможно добавить менее 1-го товара в корзину");
        }
        int stock = productDAO.getStock(productId);
        if (stock == -1) {
            throw new ServiceException("Данный товар на складе не найден");
        }
        CartItem existing = cartItemDAO.getCartItemByUserAndProduct(userId, productId);
        int inCart = (existing != null) ? existing.getQuantity() : 0;
        if (stock < inCart + quantity) {
            throw new ServiceException("Недостаточно товара на складе");
        }
        if (existing != null) {
            existing.setQuantity(inCart + quantity);
            cartItemDAO.updateQuantity(existing.getId(), existing.getQuantity());
            return existing;
        }
        CartItem cartItem = new CartItem(userId, productId, quantity);
        int id = cartItemDAO.save(cartItem);
        cartItem.setId(id);
        return cartItem;
    }

    public CartItem getCartItem(int userId, int productId) {
        return cartItemDAO.getCartItemByUserAndProduct(userId, productId);
    }

    public void increaseValueInCart(int userId, int productId, int value) {
        if (value <= 0) {
            throw new ServiceException("Значение для увеличения должно быть больше нуля");
        }

        CartItem cartItem = cartItemDAO.getCartItemByUserAndProduct(userId, productId);
        int currentQuantity = (cartItem != null) ? cartItem.getQuantity() : 0;
        int newQuantity = currentQuantity + value;

        int stock = productDAO.getStock(productId);
        if (stock != -1 && stock < newQuantity) {
            throw new ServiceException("На складе недостаточно товара");
        }

        try {
            if (cartItem == null) {
                cartItem = new CartItem(userId, productId, newQuantity);
                cartItemDAO.save(cartItem);
            } else {
                cartItemDAO.updateQuantity(cartItem.getId(), newQuantity);
            }
        } catch (DAOException e) {
            throw new ServiceException("Ошибка обновления кол-ва товара в корзине", e);
        }
    }

    public void decreaseValueInCart(int userId, int productId, int value) {
        if (value <= 0) {
            throw new ServiceException("Значение для уменьшения должно быть больше нуля");
        }
        CartItem cartItem = cartItemDAO.getCartItemByUserAndProduct(userId, productId);
        if (cartItem == null) {
            throw new ServiceException("Такого объекта у такого пользователя не существует");
        }
        int newQuantity = cartItem.getQuantity() - value;
        try {
            if (newQuantity <= 0) {
                cartItemDAO.delete(cartItem.getId());
            } else {
                cartItemDAO.updateQuantity(cartItem.getId(), newQuantity);
            }
        } catch (DAOException e) {
            throw new ServiceException("Ошибка обновления кол-ва товара в корзине", e);
        }
    }
}
