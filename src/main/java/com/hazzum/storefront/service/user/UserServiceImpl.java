package com.hazzum.storefront.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.UserRepository;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.rest.exceptionHandler.exceptionHandler.NotFoundException;

@Component
public class UserServiceImpl implements UserService{

    private UserRepository UserRepository;

    @Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		UserRepository = theUserRepository;
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
    public User SignUp(User theCustomer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User SignIn(String userName, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User deleteUser(int id) {
        UserRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUser(User theCustomer) {
        return UserRepository.save(theCustomer);
    }

    @Override
    public List<Order> getActiveOrders(int id) {
        return UserRepository.getActiveOrders(id);
    }
    
}
