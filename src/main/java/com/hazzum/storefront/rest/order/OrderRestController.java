package com.hazzum.storefront.rest.order;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.rest.exceptionHandler.InternalServerErrorException;
import com.hazzum.storefront.rest.exceptionHandler.NotAuthorizedException;
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

    @PostMapping("")
    public Order createOrder(@RequestBody Order theOrder, Principal principal) {
        Long user_id = UserService.getByName(principal.getName()).getId();
        try {
            return orderService.createOrder(theOrder, user_id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create order");
        }
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable Long orderId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null) {
            throw new NotFoundException("Order id not found - " + orderId);
        }
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        return theOrder;
    }

    @PutMapping("{orderId}")
    public Order updateOrder(@RequestBody Order theOrder, @PathVariable Long orderId, Principal principal) {
        Order tempOrder = orderService.getOrder(orderId);
        // throw exception if null
        if (tempOrder == null)
            throw new NotFoundException("Order id not found - " + orderId);
        // validate user id
        if (!tempOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
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
    public String deleteOrder(@PathVariable Long orderId, Principal principal) {
        Order tempOrder = orderService.getOrder(orderId);
        // throw exception if null
        if (tempOrder == null)
            throw new NotFoundException("Order id not found - " + orderId);
        // validate user id
        if (!tempOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        try {
            orderService.deleteOrder(orderId);
            return "Deleted Order id - " + orderId;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not delete order");
        }
    }
}
