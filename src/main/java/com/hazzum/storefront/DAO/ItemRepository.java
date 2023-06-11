package com.hazzum.storefront.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hazzum.storefront.entity.Item;
import com.hazzum.storefront.payload.response.CartItem;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT    new com.hazzum.storefront.payload.response.CartItem(i.id, p.id, name, url, description, price, quantity) " +
            "FROM      Product p INNER JOIN Item i " +
            "ON        i.product_id = p.id " +
            "AND       i.order_id = :orderid " +
            "ORDER BY  i.id DESC")
    List<CartItem> showAll(@Param("orderid") Long orderID);
}
