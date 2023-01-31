package com.hazzum.storefront.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazzum.storefront.DAO.ItemRepository;
import com.hazzum.storefront.entity.Item;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository) {
        this.itemRepository = theItemRepository;
    }

    @Override
    public List<Item> showAll(int orderID) {
        return itemRepository.showAll(orderID);
    }

    @Override
    public Item findById(int orderID, int itemID) {
        return itemRepository.getById(orderID, itemID);
    }

    @Override
    public Item addItem(int quantity, int orderID, int productID) {
        return itemRepository.addItem(quantity, orderID, productID);
    }

    @Override
    public Item updateQuantity(int itemID, int quantity) {
        return itemRepository.updateItem(itemID, quantity);
    }

    @Override
    public Item removeItem(int orderID, int itemID) {
        return itemRepository.removeItem(orderID, itemID);
    }
    
}
