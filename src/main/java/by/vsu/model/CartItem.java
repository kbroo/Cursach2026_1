package by.vsu.model;

import java.math.BigDecimal;

public class CartItem {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private String productName;
    private BigDecimal productPrice;
    private String imageUrl;

    public CartItem(int id, int userId, int productId, int quantity,
                    String productName, BigDecimal productPrice, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
    }
    public CartItem(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getProductName() {
        return productName;
    }
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
