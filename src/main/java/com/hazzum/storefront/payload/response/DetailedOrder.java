package com.hazzum.storefront.payload.response;

import java.util.List;

public class DetailedOrder {
    private Long order_id;
    private String order_status;
    private List<CartItem> order_details;

    public Long getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public List<CartItem> getOrder_details() {
        return order_details;
    }
    public void setOrder_details(List<CartItem> order_details) {
        this.order_details = order_details;
    }

    
}
