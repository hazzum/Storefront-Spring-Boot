package com.hazzum.storefront.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.OrderRepository;
import com.hazzum.storefront.entity.Order;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository theOrderRepository) {
        orderRepository = theOrderRepository;
    }

    @Override
    public List<Order> index() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> getActive() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order createOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order updatOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order deletOrder() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
