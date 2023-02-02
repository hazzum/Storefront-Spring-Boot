package com.hazzum.storefront.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.DetailedOrder;

@Service
public interface UserService {

    List<User> index();

    User getUser(int id);

    User getByName(String name);

    List<DetailedOrder> getActiveOrders(int id);

    List<DetailedOrder> getHistory(int id);

    Order addOrder(int id, String status);

    User saveUser(User theUser);

    User deleteUser(int id);

    User updateUser(User theUser);
}
