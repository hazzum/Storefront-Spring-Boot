package com.hazzum.storefront.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazzum.storefront.entity.CartItem;
import com.hazzum.storefront.entity.DetailedOrder;
import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.Product;
import com.hazzum.storefront.entity.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // define field for entity manager
	private EntityManager entityManager;
	
	// set up a constructor injection 
	@Autowired
	public UserRepositoryImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    public List<CartItem> getCartItems(int orderID) {
        Session currentSession = entityManager.unwrap(Session.class);
        Order theOrder = currentSession.get(Order.class, orderID);
        List<Item> theItems = theOrder.getItems();
        List<CartItem> theOrders = new ArrayList<CartItem>();
        for(Item item: theItems) {
            CartItem cartItem = new CartItem();
            Product product = item.getProduct();
            cartItem.setItem_id(item.getId());
            cartItem.setProduct_id(product.getId());
            cartItem.setName(product.getName());
            cartItem.setUrl(product.getUrl());
            cartItem.setPrice(product.getPrice());
            cartItem.setDescription(product.getDescription());
            cartItem.setQuantity(item.getQuantity());
            theOrders.add(cartItem);
        }
        return theOrders;
    }

    @Override
    public List<User> findAll() {
        // get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<User> theQuery = 
				currentSession.createQuery("from users",User.class);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();
		
		//return the result
		return users;
    }

    @Override
    public User findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(User.class, theId);
    }

    @Override
    public User save(User theUser) {
        // get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save the employee
		currentSession.persist(theUser);

        return theUser;
    }

    @Override
    public User update(User theUser) {
        // get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		User original = currentSession.get(User.class, theUser.getId());
        theUser.setId(original.getId());
        theUser.setPassword(original.getPassword());
		// save the employee
		currentSession.persist(theUser);

        return theUser;
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
		// delete the object with primary key
		currentSession.remove(theUser);        
    }

    @Override
    public List<DetailedOrder> showActiveOrders(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        List<Order> theOrders = theUser.getOrders()
            .stream()
            .filter(order -> order.getStatus().equals("active"))
            .collect(Collectors.toList());
        List<DetailedOrder> theDetailedOrders = new ArrayList<DetailedOrder>();
        for(Order order: theOrders) {
            DetailedOrder detailedOrder = new DetailedOrder();
            detailedOrder.setOrder_id(order.getId());
            detailedOrder.setOrder_status(order.getStatus());
            detailedOrder.setOrder_details(getCartItems(order.getId()));
            theDetailedOrders.add(detailedOrder);
        }
        return theDetailedOrders;
    }

    @Override
    public List<DetailedOrder> showHistory(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        List<Order> theOrders = theUser.getOrders()
            .stream()
            .filter(order -> order.getStatus().equals("complete"))
            .collect(Collectors.toList());
        List<DetailedOrder> theDetailedOrders = new ArrayList<DetailedOrder>();
        for(Order order: theOrders) {
            DetailedOrder detailedOrder = new DetailedOrder();
            detailedOrder.setOrder_id(order.getId());
            detailedOrder.setOrder_status(order.getStatus());
            detailedOrder.setOrder_details(getCartItems(order.getId()));
            theDetailedOrders.add(detailedOrder);
        }
        return theDetailedOrders;
    }

    @Override
    public Order addOrder(int theId, String status) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        Order theOrder = new Order(status);
        theUser.add(theOrder);
        currentSession.persist(theOrder);
        currentSession.persist(theUser);
        return theOrder;
    }
    
}
