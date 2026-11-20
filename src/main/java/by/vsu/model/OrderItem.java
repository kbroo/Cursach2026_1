package by.vsu.model;

import by.vsu.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal priceAtMoment;
    private LocalDateTime createdAt;

    public OrderItem(int orderId, int productId, int quantity, BigDecimal priceAtMoment, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtMoment = priceAtMoment;
        this.createdAt = createdAt;
    }

    public OrderItem(int id, int orderId, int productId, int quantity, BigDecimal priceAtMoment, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtMoment = priceAtMoment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public int getOrderId() {
        return orderId;
    }
    public int getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public BigDecimal getPriceAtMoment() {
        return priceAtMoment;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPriceAtMoment(BigDecimal priceAtMoment) {
        this.priceAtMoment = priceAtMoment;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        OrderItem orderItem = (OrderItem) obj;
        return id == orderItem.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
