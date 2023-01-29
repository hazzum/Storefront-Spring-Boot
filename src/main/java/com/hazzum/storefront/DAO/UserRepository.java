package com.hazzum.storefront.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
    @Query("SELECT * FROM orders WHERE user_id=?1 AND status='active'")
    List<Order> getActiveOrders(int id);
}
