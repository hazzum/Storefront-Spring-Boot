package com.hazzum.storefront.DAO;
import java.util.List;

import com.hazzum.storefront.entity.Item;

public interface ItemRepository {

    List<Item> showAll(int orderID);
    
    Item addItem(int quantity, int orderID, int productID);

    Item removeItem(int itemID);

    Item updateItem(int itemID, int quantity);
}
