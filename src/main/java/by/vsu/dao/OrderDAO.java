package by.vsu.dao;

import by.vsu.model.CartItem;
import by.vsu.model.Order;
import by.vsu.model.OrderItem;

import java.util.List;

public interface OrderDAO {
    public int save(Order order, List<CartItem> items);
    public List<Order> getOrdersByUserId(int id);
    public List<OrderItem> getOrderItems(int id);
}
