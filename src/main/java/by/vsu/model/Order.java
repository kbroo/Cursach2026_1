package by.vsu.model;

import by.vsu.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private int userId;
    private BigDecimal totalPrice;
    private String address;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order(int userId, BigDecimal totalPrice, String address, OrderStatus status, LocalDateTime createdAt) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order(int id, int userId, BigDecimal totalPrice, String address, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public String getAddress() {
        return address;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
