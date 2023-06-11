package com.hazzum.storefront.service.order;

import com.hazzum.storefront.entity.Order;

public interface OrderService {

    Order getOrder(Long theId);

    Order createOrder(Order theOrder, Long user_id);

    Order updateOrder(Order theOrder);

    Order deleteOrder(Long theId);
}
