package com.hazzum.storefront.rest.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.Product;
import com.hazzum.storefront.rest.exceptionHandler.InternalServerErrorException;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.product.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    // add mapping for GET /product
    @GetMapping("")
    public List<Product> getProduct() {
        List<Product> theProducts = productService.index();
        if (theProducts.isEmpty())
            throw new NotFoundException("No products found");
        return theProducts;
    }

    // add mapping for GET /products/{productId}
    @GetMapping("{productId}")
    public Product getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }

    // add mapping for POST /products - add new product
    @PostMapping("")
    public Product addProduct(@RequestBody Product theProduct) {
        try {
            return productService.createProduct(theProduct);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create product");
        }
    }

    // add mapping for PUT /products - update existing product
    @PutMapping("{productId}")
    public Product updateProduct(@RequestBody Product theProduct, @PathVariable int productId) {
        Product tempProduct = productService.getProduct(productId);
        // throw exception if null
        if (tempProduct == null)
            throw new NotFoundException("Product id not found - " + productId);
        // update product
        theProduct.setId(productId);
        try {
            return productService.updateProduct(theProduct);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not update product");
        }
    }

    // add mapping Delete /products/{productId} - delete existing product
    @DeleteMapping("{productId}")
    public String deleteProduct(@PathVariable int productId) {
        Product tempProduct = productService.getProduct(productId);
        // throw exception if null
        if (tempProduct == null)
            throw new NotFoundException("Product id not found - " + productId);
        try {
            productService.deleteProduct(productId);
            return "Deleted Product id - " + productId;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not delete product");
        }
    }
}
