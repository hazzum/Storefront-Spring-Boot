package com.hazzum.storefront.service.item;

import java.util.List;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.payload.response.CartItem;

public interface ItemService {
    List<CartItem> showAll(Long orderID);
    Item findById(Long itemID);
    Item addItem(int quantity, Long orderID, Long productID);
    Item updateQuantity(Long itemID, int quantity);
    void removeItem(Long itemID);
    void commitOrder(Long theId);
}
