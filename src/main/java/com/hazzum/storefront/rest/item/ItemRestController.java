package com.hazzum.storefront.rest.item;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.item.ItemService;
import com.hazzum.storefront.service.order.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemRestController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}/items")
    public List<Item> index(@PathVariable int orderId) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null) throw new NotFoundException("No such order exists");
        return itemService.showAll(orderId);
    }

    @PutMapping("{orderId}/items/{itemId}")
    public Item updateItem(@PathVariable int orderId, @PathVariable int itemId, @RequestBody Item theItem) {
        Order theOrder = orderService.getOrder(orderId);
        if (theOrder == null) throw new NotFoundException("No such order exist");
        Item original = itemService
            .showAll(orderId)
            .stream()
            .filter(item -> item.getId() == itemId)
            .collect(Collectors.toList()).get(0);
        if (original == null) throw new NotFoundException("No such item exist");
        return itemService.updateQuantity(itemId, theItem.getQuantity());
    }
    
}
