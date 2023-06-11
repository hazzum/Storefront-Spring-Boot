package com.hazzum.storefront.payload.response;

public class CartItem {
    private Long item_id;
    private Long product_id;
    private String name;
    private String url;
    private String description;
    private float price;
    private int quantity;
    
    public CartItem() {
        
    }

    public CartItem(Long item_id, Long product_id, String name, String url, String description, float price,
            int quantity) {
        this.item_id = item_id;
        this.product_id = product_id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getItem_id() {
        return item_id;
    }
    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
