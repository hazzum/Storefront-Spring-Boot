package com.hazzum.storefront.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hazzum.storefront.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdAsc();
    
}
