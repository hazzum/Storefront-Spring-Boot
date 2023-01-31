package com.hazzum.storefront.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazzum.storefront.DAO.UserRepository;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.DetailedOrder;

@Service
@Transactional
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
        return UserRepository.findById(id);
    }

    @Override
    public User deleteUser(int id) {
        UserRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUser(User theCustomer) {
        return UserRepository.update(theCustomer);
    }

    @Override
    public List<DetailedOrder> getActiveOrders(int id) {
        return UserRepository.showActiveOrders(id);
    }

    @Override
    public List<DetailedOrder> getHistory(int id) {
        return UserRepository.showHistory(id);
    }

    @Override
    public Order addOrder(int id, String status) {
        Order theOrder = UserRepository.addOrder(id, status);
        return theOrder;
    }
    
}
