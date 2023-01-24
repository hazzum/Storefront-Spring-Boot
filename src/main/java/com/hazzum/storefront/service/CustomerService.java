package com.hazzum.storefront.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hazzum.storefront.entity.Customer;

@Service
public interface CustomerService {

    List<Customer> index();

    Customer getCustomer(int id);

    Customer createCustomer(Customer theCustomer);

    Customer deleteCustomer(int id);

    Customer updateCustomer(Customer theCustomer);
}
