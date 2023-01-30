package com.hazzum.storefront.service.item;

import java.util.List;

import com.hazzum.storefront.entity.Item;

public interface ItemService {
    List<Item> showAll(int orderID);
    Item addItem(int quantity, int orderID, int productID);
    Item updateQuantity(int itemID, int quantity);
    Item removeItem(int itemID);
}
