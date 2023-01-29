package com.hazzum.storefront.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hazzum.storefront.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
