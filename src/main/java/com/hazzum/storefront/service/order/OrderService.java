package com.hazzum.storefront.service.order;

import com.hazzum.storefront.entity.Order;

public interface OrderService {

    Order getOrder(int theId);

    Order updateOrder(Order theOrder);

    Order deleteOrder(int theId);
}
