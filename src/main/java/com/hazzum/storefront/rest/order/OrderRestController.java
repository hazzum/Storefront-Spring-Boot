package com.hazzum.storefront.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.rest.exceptionHandler.InternalServerErrorException;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.order.OrderService;
import com.hazzum.storefront.service.user.UserService;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService UserService;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Order createOrder(@RequestBody Order theOrder) {
        try {
            return UserService.addOrder(1, theOrder.getStatus());
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create order");
        }
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null)
            throw new NotFoundException("Order id not found - " + orderId);
        return theOrder;
    }

    @PutMapping("{orderId}")
    public Order updateOrder(@RequestBody Order theOrder, @PathVariable int orderId) {
        Order tempOrder = orderService.getOrder(orderId);
        // throw exception if null
        if (tempOrder == null)
            throw new NotFoundException("Order id not found - " + orderId);
        // update order
        theOrder.setId(orderId);
        try {
            orderService.updateOrder(theOrder);
            return theOrder;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not update order");
        }
    }

    // add mapping Delete /orders/{orderId} - delete existing order
    @DeleteMapping("{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        Order tempOrder = orderService.getOrder(orderId);
        // throw exception if null
        if (tempOrder == null)
            throw new NotFoundException("Order id not found - " + orderId);
        try {
            orderService.deleteOrder(orderId);
            return "Deleted Order id - " + orderId;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not delete order");
        }
    }
}
