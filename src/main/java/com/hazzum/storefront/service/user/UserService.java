package com.hazzum.storefront.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.DetailedOrder;

@Service
public interface UserService {

    List<User> index();

    User getUser(Long userId);

    User getByName(String name);

    List<DetailedOrder> getActiveOrders(Long id);

    List<DetailedOrder> getHistory(Long id);

    User saveUser(User theUser);

    User deleteUser(Long id);

    User updateUser(User theUser);
}
