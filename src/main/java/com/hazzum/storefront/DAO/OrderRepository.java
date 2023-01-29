package com.hazzum.storefront.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hazzum.storefront.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
