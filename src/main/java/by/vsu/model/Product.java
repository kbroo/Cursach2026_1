package by.vsu.model;

import by.vsu.model.enums.Category;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String description;
    private Category category;
    private String imageUrl;
    private BigDecimal price;
    private int stock;

    public Product(int id, String name, String description, Category category,
                   String imageUrl, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Category getCategory() {
        return category;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Product object = (Product) obj;
        return id == object.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
