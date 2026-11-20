package by.vsu.service;

import by.vsu.dao.CartDAO;
import by.vsu.dao.OrderDAO;
import by.vsu.dao.impl.CartDAOImpl;
import by.vsu.dao.impl.OrderDAOImpl;
import by.vsu.dto.Cart;
import by.vsu.exception.DAOException;
import by.vsu.exception.ServiceException;
import by.vsu.model.Order;
import by.vsu.model.OrderItem;
import by.vsu.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CartService cartService = new CartService();

    public int create(int userId, String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new ServiceException("Не указан адрес доставки.");
        }
        Cart cart = cartService.getCartOfUser(userId);
        if (cart.getItems().isEmpty()) {
            throw new ServiceException("Корзина пуста");
        }

        Order order = new Order(userId, cart.getTotalPrice(), address, OrderStatus.NEW, LocalDateTime.now());

        try {
            return orderDAO.save(order, cart.getItems());
        } catch (DAOException e) {
            throw new ServiceException("Не удалось оформить заказ.", e);
        }
    }

    public List<Order> getUserOrders(int userId) {
        try {
            return orderDAO.getOrdersByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException("Не удалось загрузить историю заказов", e);
        }
    }

    public List<OrderItem> getOrderDetails(int orderId) {
        try {
            return orderDAO.getOrderItems(orderId);
        } catch (DAOException e) {
            throw new ServiceException("Не удалось загрузить детали заказа", e);
        }
    }
}
