package com.hazzum.storefront.service.product;

import com.hazzum.storefront.entity.Product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    
    List<Product> index();

    Product getProduct(int id);

    Product createProduct(Product theProduct);

    Product deleteProduct(int id);

    Product updateProduct(Product theProduct);
}
