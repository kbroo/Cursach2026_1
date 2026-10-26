package by.vsu.dto;

import by.vsu.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart(List<CartItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            BigDecimal itemSum = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemSum);
        }
        return total;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
