package com.hazzum.storefront.service.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.ItemRepository;
import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.payload.response.CartItem;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;


@Component
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository) {
        this.itemRepository = theItemRepository;
    }

    @Override
    public List<CartItem> showAll(int orderID) {
        return itemRepository.showAll(orderID);
    }

    @Override
    public Item findById(int itemID) {
        Optional<Item> result = itemRepository.findById(itemID);
        Item theItem = null;
		if (result.isPresent()) {
			theItem = result.get();
			return theItem;
		} else {
			throw new NotFoundException("Item not found id: " + itemID);
		}
    }

    @Override
    public Item addItem(int quantity, int orderID, int productID) {
        Item theItem = new Item(quantity);
        theItem.setOrder_id(orderID);
        theItem.setProduct_id(productID);
        return itemRepository.save(theItem);
    }

    @Override
    public Item updateQuantity(int itemID, int quantity) {
        Item theItem = findById(itemID);
        theItem.setQuantity(quantity);
        return itemRepository.save(theItem);
    }

    @Override
    public void removeItem(int itemID) {
        itemRepository.deleteById(itemID);
    }
    
}
