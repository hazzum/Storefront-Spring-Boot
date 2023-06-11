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
    public Order getOrder(Long theId) {
        Optional<Order> result = orderRepository.findById(theId);
        Order theOrder = null;
        if (result.isPresent()) {
            theOrder = result.get();
            return theOrder;
        } else {
            throw new NotFoundException("Order not found id: " + theId);
        }
    }
    
    @Override
    public Order createOrder(Order theOrder, Long userID) {
        theOrder.setUser_id(userID);
        return orderRepository.save(theOrder);
    }

    @Override
    public Order updateOrder(Order theOrder) {
        Order original = getOrder(theOrder.getId());
        original.setStatus(theOrder.getStatus());
        return orderRepository.save(original);
    }

    @Override
    public Order deleteOrder(Long theId) {
        try {getOrder(theId);} finally {}
        orderRepository.deleteById(theId);
        return null;
    }

}
