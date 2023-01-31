package com.hazzum.storefront.DAO;
import java.util.List;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.payload.response.CartItem;

public interface ItemRepository {

    List<CartItem> showAll(int orderID);

    Item getById(int orderID, int itemID);
    
    Item addItem(int quantity, int orderID, int productID);

    Item removeItem(int orderID, int itemID);

    Item updateItem(int itemID, int quantity);
}
