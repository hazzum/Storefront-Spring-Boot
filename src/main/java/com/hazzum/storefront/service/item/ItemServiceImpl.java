package com.hazzum.storefront.service.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.ItemRepository;
import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.Product;
import com.hazzum.storefront.payload.response.CartItem;
import com.hazzum.storefront.rest.exceptionHandler.BadRequestException;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.order.OrderService;
import com.hazzum.storefront.service.product.ProductService;

@Component
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository, ProductService theProductService,
            OrderService theOrderService) {
        this.itemRepository = theItemRepository;
        this.productService = theProductService;
        this.orderService = theOrderService;
    }

    @Override
    public List<CartItem> showAll(Long orderID) {
        return itemRepository.showAll(orderID);
    }

    @Override
    public Item findById(Long itemID) {
        Optional<Item> result = itemRepository.findById(itemID);
        Item theItem = null;
        if (result.isPresent()) {
            theItem = result.get();
            return theItem;
        } else {
            throw new NotFoundException("Item not found id: " + itemID);
        }
    }

    @Override
    public Item addItem(int quantity, Long orderID, Long productID) {
        Order theOrder = orderService.getOrder(orderID);
        if (!theOrder.getStatus().equals("active")) {
            throw new BadRequestException("cannot modify an item in a closed order");
        }
        Product theProduct = productService.getProduct(productID);
        if (quantity > theProduct.getStock()) {
            throw new BadRequestException(
                    "There's not enough stock to make this order. Only " + theProduct.getStock() + " units remaining");
        }
        Item theItem = new Item(quantity);
        theItem.setOrder_id(orderID);
        theItem.setProduct_id(productID);
        productService.updateProduct(theProduct);
        return itemRepository.save(theItem);
    }

    @Override
    public Item updateQuantity(Long itemID, int quantity) {
        Item theItem = findById(itemID);
        Order theOrder = orderService.getOrder(theItem.getOrder_id());
        if (!theOrder.getStatus().equals("active")) {
            throw new BadRequestException("cannot modify an item in a closed order");
        }
        Product theProduct = productService.getProduct(theItem.getProduct_id());
        if (quantity > theProduct.getStock()) {
            throw new BadRequestException(
                    "There's not enough stock to make this order. Only " + theProduct.getStock() + " units remaining");
        }
        theItem.setQuantity(quantity);
        return itemRepository.save(theItem);
    }

    @Override
    public void removeItem(Long itemID) {
        Item theItem = findById(itemID);
        Order theOrder = orderService.getOrder(theItem.getOrder_id());
        if (!theOrder.getStatus().equals("active")) {
            throw new BadRequestException("cannot modify an item in a closed order");
        }
        itemRepository.deleteById(itemID);
    }

    @Override
    public void commitOrder(Long orderID) {
        Order theOrder = orderService.getOrder(orderID);
        List<CartItem> theCartItems = showAll(orderID);
        for(CartItem i: theCartItems) {
            Product theProduct = productService.getProduct(i.getProduct_id());
            theProduct.setStock(theProduct.getStock()-i.getQuantity());
            productService.updateProduct(theProduct);
        }
        theOrder.setStatus("complete");
        orderService.updateOrder(theOrder);
    }

}
