package com.hazzum.storefront.DAO;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazzum.storefront.entity.Order;
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
    public List<Order> showActiveOrders(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        List<Order> theOrders = theUser.getOrders()
            .stream()
            .filter(order -> order.getStatus() == "active")
            .collect(Collectors.toList());
        return theOrders;
    }

    @Override
    public List<Order> showHistory(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        User theUser = currentSession.get(User.class, theId);
        List<Order> theOrders = theUser.getOrders()
            .stream()
            .filter(order -> order.getStatus() == "complete")
            .collect(Collectors.toList());
        return theOrders;
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
