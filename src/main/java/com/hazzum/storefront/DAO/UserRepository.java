package com.hazzum.storefront.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hazzum.storefront.entity.Order;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.response.CartItem;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT     new com.hazzum.storefront.payload.response.CartItem(i.id, p.id, name, url, description, price, quantity) " +
            "FROM      Product p INNER JOIN Item i " +
            "ON        i.product_id = p.id " +
            "AND       i.order_id = :orderid " +
            "ORDER BY  i.id DESC")
    List<CartItem> getCartItems(@Param("orderid") int orderID);
    
    public Optional<User> findByUserName(String userName);

    @Query("FROM Order o WHERE o.user_id=:userid AND o.status=\"active\"")
    public List<Order> showActiveOrders(@Param("userid") int theId);

    @Query("FROM Order o WHERE o.user_id=:userid AND o.status=\"complete\" ORDER BY o.id DESC")
    public List<Order> showHistory(@Param("userid") int theId);
}
