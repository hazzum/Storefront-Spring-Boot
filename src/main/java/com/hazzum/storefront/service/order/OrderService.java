package com.hazzum.storefront.service.order;

import java.util.List;

import com.hazzum.storefront.entity.Order;

public interface OrderService {
    List<Order> index();

    Order getOrder();

    List<Order> getActive();

    Order createOrder();

    Order updatOrder();

    Order deletOrder();
}
