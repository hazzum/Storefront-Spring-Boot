package com.hazzum.storefront.rest.item;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.payload.response.CartItem;
import com.hazzum.storefront.rest.exceptionHandler.BadRequestException;
import com.hazzum.storefront.rest.exceptionHandler.NotAuthorizedException;
import com.hazzum.storefront.service.item.ItemService;
import com.hazzum.storefront.service.order.OrderService;

@RestController
@RequestMapping("/orders")
public class ItemRestController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @PostMapping("{orderId}/items")
    public Item createItem(@PathVariable String orderId, @RequestBody Item theItem, Principal principal) {
        Order theOrder = orderService.getOrder(Integer.parseInt(orderId));
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        return itemService.addItem(theItem.getQuantity(), Integer.parseInt(orderId), theItem.getProduct_id());
    }

    @PostMapping("{orderId}/items/commit")
    public Order commitOrder(@PathVariable String orderId, Principal principal) {
        Order theOrder = orderService.getOrder(Integer.parseInt(orderId));
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        itemService.commitOrder(Integer.parseInt(orderId));
        return orderService.getOrder(Integer.parseInt(orderId));
    }

    @GetMapping("{orderId}/items")
    public List<CartItem> index(@PathVariable int orderId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        return itemService.showAll(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    public Item getItem(@PathVariable int orderId, @PathVariable int itemId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item original;
        try {
            original = itemService.findById(itemId);
        } finally {
        }
        if(original.getOrder_id()!=theOrder.getId()) {
            throw new BadRequestException("Mismatched Order");
        }
        return original;
    }

    @PutMapping("{orderId}/items/{itemId}")
    public Item updateItem(@PathVariable int orderId, @PathVariable int itemId, @RequestBody Item theItem,
            Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item original;
        try {
            original = itemService.findById(itemId);
        } finally {
        }
        if(original.getOrder_id()!=theOrder.getId()) {
            throw new BadRequestException("Mismatched Order");
        }
        return itemService.updateQuantity(itemId, theItem.getQuantity());
    }

    @DeleteMapping("{orderId}/items/{itemId}")
    public Item deleteItem(@PathVariable int orderId, @PathVariable int itemId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item original;
        try {
            original = itemService.findById(itemId);
        } finally {
        }
        if(original.getOrder_id()!=theOrder.getId()) {
            throw new BadRequestException("Mismatched Order");
        }
        itemService.removeItem(itemId);
        return null;
    }

}
