package com.hazzum.storefront.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.UserRepository;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.DetailedOrder;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;

@Component
public class UserServiceImpl implements UserService{

    private UserRepository UserRepository;

    @Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		this.UserRepository = theUserRepository;
	}

    @Override
    public List<User> index() {
        return UserRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        Optional<User> result = UserRepository.findById(id);

		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
			return theUser;
		} else {
			throw new NotFoundException("User not found id: " + id);
		}
    }

    @Override
    public User getByName(String name) {
        Optional<User> result = UserRepository.findByUserName(name);

		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
			return theUser;
		} else {
			throw new NotFoundException("User not found user name: " + name);
		}
    }

    @Override
    public User saveUser(User theUser) {
        return UserRepository.save(theUser);
    }

    @Override
    public User deleteUser(int id) {
        UserRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUser(User theUser) {
        return UserRepository.save(theUser);
    }

    @Override
    public List<DetailedOrder> getActiveOrders(int id) {
        List<Order> theOrders = UserRepository.showActiveOrders(id);
        List<DetailedOrder> theDetailedOrders = new ArrayList<DetailedOrder>();
        for(Order order: theOrders) {
            DetailedOrder detailedOrder = new DetailedOrder();
            detailedOrder.setOrder_id(order.getId());
            detailedOrder.setOrder_status(order.getStatus());
            detailedOrder.setOrder_details(UserRepository.getCartItems(order.getId()));
            theDetailedOrders.add(detailedOrder);
        }
        return theDetailedOrders;
    }

    @Override
    public List<DetailedOrder> getHistory(int id) {
        List<Order> theOrders = UserRepository.showHistory(id);
        List<DetailedOrder> theDetailedOrders = new ArrayList<DetailedOrder>();
        for(Order order: theOrders) {
            DetailedOrder detailedOrder = new DetailedOrder();
            detailedOrder.setOrder_id(order.getId());
            detailedOrder.setOrder_status(order.getStatus());
            detailedOrder.setOrder_details(UserRepository.getCartItems(order.getId()));
            theDetailedOrders.add(detailedOrder);
        }
        return theDetailedOrders;
    }
    
}
