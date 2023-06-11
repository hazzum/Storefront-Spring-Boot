package com.hazzum.storefront.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.ProductRepository;
import com.hazzum.storefront.entity.Product;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;

@Component
public class ProductServiceImpl implements ProductService {

    private ProductRepository ProductRepository;

    @Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		ProductRepository = theProductRepository;
	}

    @Override
    public List<Product> index() {
        return ProductRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> result = ProductRepository.findById(id);

		Product theProduct = null;

		if (result.isPresent()) {
			theProduct = result.get();
			return theProduct;
		} else {
			throw new NotFoundException("Product not found id: " + id);
		}
    }

    @Override
    public Product createProduct(Product theProduct) {
        return ProductRepository.save(theProduct);
    }

    @Override
    public Product deleteProduct(Long id) {
        ProductRepository.deleteById(id);
        return null;
    }

    @Override
    public Product updateProduct(Product theProduct) {
        return ProductRepository.save(theProduct);
    }
    
}
