package com.hazzum.storefront.service.item;

import java.util.List;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.payload.response.CartItem;

public interface ItemService {
    List<CartItem> showAll(int orderID);
    Item findById(int itemID);
    Item addItem(int quantity, int orderID, int productID);
    Item updateQuantity(int itemID, int quantity);
    void removeItem(int itemID);
}
