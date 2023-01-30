package com.hazzum.storefront.DAO;

import java.util.List;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;

public interface UserRepository {
    
    public List<User> findAll();

	public User findById(int theId);
	
	public User save(User theUser);

    public User update(User theUser);
	
	public void deleteById(int theId);

    public List<Order> showActiveOrders(int theId);

    public List<Order> showHistory(int theId);

    public Order addOrder(int theId, String status);

}
