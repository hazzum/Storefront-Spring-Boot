package com.hazzum.storefront.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;

@Service
public interface UserService {

    List<User> index();

    User getUser(int id);

    List<Order> getActiveOrders(int id);

    List<Order> getHistory(int id);

    Order addOrder(int id, String status);

    User SignUp(User theCustomer);

    User SignIn(String userName, String password);

    User deleteUser(int id);

    User updateUser(User theCustomer);
}
