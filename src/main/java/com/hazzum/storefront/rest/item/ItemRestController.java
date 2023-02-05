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
import com.hazzum.storefront.rest.exceptionHandler.NotAuthorizedException;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
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
        if (theOrder == null)
            throw new NotFoundException("No such order exist");
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        return itemService.addItem(theItem.getQuantity(), Integer.parseInt(orderId), theItem.getProduct_id());
    }

    @GetMapping("{orderId}/items")
    public List<CartItem> index(@PathVariable int orderId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null)
            throw new NotFoundException("No such order exists");
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        return itemService.showAll(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    public Item getItem(@PathVariable int orderId, @PathVariable int itemId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null)
            throw new NotFoundException("No such order exists");
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item theItem = itemService.findById(itemId);
        if (theItem == null)
            throw new NotFoundException("No such item exist");
        return theItem;
    }

    @PutMapping("{orderId}/items/{itemId}")
    public Item updateItem(@PathVariable int orderId, @PathVariable int itemId, @RequestBody Item theItem,
            Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null)
            throw new NotFoundException("No such order exist");
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item original = itemService.findById(itemId);
        if (original == null)
            throw new NotFoundException("No such item exist");
        return itemService.updateQuantity(itemId, theItem.getQuantity());
    }

    @DeleteMapping("{orderId}/items/{itemId}")
    public Item deleteItem(@PathVariable int orderId, @PathVariable int itemId, Principal principal) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null)
            throw new NotFoundException("No such order exist");
        // validate user id
        if (!theOrder.getUser().getUserName().equals(principal.getName())) {
            throw new NotAuthorizedException("Unauthorized");
        }
        Item original = itemService.findById(itemId);
        if (original == null)
            throw new NotFoundException("No such item exist");
        itemService.removeItem(itemId);
        return null;
    }

}
