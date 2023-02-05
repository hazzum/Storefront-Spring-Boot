package com.hazzum.storefront.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.DetailedOrder;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    public List<User> findAll();

	public User findById(int theId);

    public User findByUserName(String userName);
	
	public User save(User theUser);

    public User update(User theUser);
	
	public void deleteById(int theId);

    public List<DetailedOrder> showActiveOrders(int theId);

    public List<DetailedOrder> showHistory(int theId);

    public Order addOrder(int theId, String status);

}
