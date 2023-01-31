package com.hazzum.storefront.DAO;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.Product;

import jakarta.persistence.EntityManager;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    // define field for entity manager
	private EntityManager entityManager;
	
	// set up a constructor injection 
	@Autowired
	public ItemRepositoryImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
    public List<Item> showAll(int orderID) {
        Session currentSession = entityManager.unwrap(Session.class);
        Order theOrder = currentSession.get(Order.class, orderID);
        return theOrder.getItems();
    }

    @Override
    public Item getById(int orderID, int itemID) {
        Session currentSession = entityManager.unwrap(Session.class);
        Order theOrder = currentSession.get(Order.class, orderID);
        Item theItem = theOrder.getItems().stream().filter(i -> i.getId() == itemID).collect(Collectors.toList()).get(0);
        return theItem;
    }

    @Override
    public Item addItem(int quantity, int orderID, int productID) {
        Session currentSession = entityManager.unwrap(Session.class);
        Product theProduct = currentSession.get(Product.class, productID);
        Order theOrder = currentSession.get(Order.class, orderID);
        Item theItem = new Item(quantity);
        theItem.setProduct(theProduct);
        theOrder.add(theItem);
        currentSession.persist(theItem);
        currentSession.persist(theOrder);
        return theItem;
    }

    @Override
    public Item removeItem(int orderID, int itemID) {
        Session currentSession = entityManager.unwrap(Session.class);
        Order theOrder = currentSession.get(Order.class, orderID);
        Item theItem = currentSession.get(Item.class, itemID);
        theOrder.setItems(theOrder.getItems().stream().filter(i -> i.getId() != itemID).collect(Collectors.toList()));
        currentSession.remove(theItem);
        currentSession.persist(theOrder);
        return null;
    }

    @Override
    public Item updateItem(int itemID, int quantity) {
        Session currentSession = entityManager.unwrap(Session.class);
        Item theItem = currentSession.get(Item.class, itemID);
        theItem.setQuantity(quantity);
        currentSession.persist(theItem);
        return theItem;
    }
    
}
