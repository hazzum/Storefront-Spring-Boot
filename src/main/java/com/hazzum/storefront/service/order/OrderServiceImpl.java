package com.hazzum.storefront.service.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.OrderRepository;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository theOrderRepository) {
        orderRepository = theOrderRepository;
    }

    @Override
    public Order getOrder(int theId) {
        Optional<Order> result = orderRepository.findById(theId);
        Order theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
            return theUser;
        } else {
            throw new NotFoundException("Order not found id: " + theId);
        }
    }

    @Override
    public Order updateOrder(Order theOrder) {
        return orderRepository.save(theOrder);
    }

    @Override
    public Order deleteOrder(int theId) {
        orderRepository.deleteById(theId);
        return null;
    }

}
