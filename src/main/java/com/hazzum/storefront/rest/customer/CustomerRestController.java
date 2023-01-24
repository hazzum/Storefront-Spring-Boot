package com.hazzum.storefront.rest.customer;

import com.hazzum.storefront.entity.Customer;
import com.hazzum.storefront.rest.exceptionHandler.exceptionHandler.InternalServerErrorException;
import com.hazzum.storefront.rest.exceptionHandler.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    // add mapping for GET /customer
    @GetMapping("")
    public List<Customer> getCustomer() {
        List<Customer> theCustomers = customerService.index();
        if (theCustomers.isEmpty())
            throw new NotFoundException("No customers found");
        return theCustomers;
    }

    // add mapping for GET /customers/{customerId}
    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        return customerService.getCustomer(customerId);
    }

    // add mapping for POST /customers - add new customer
    @PostMapping("")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        try {
            customerService.createCustomer(theCustomer);
            return theCustomer;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create customer");
        }
    }

    // add mapping for PUT /customers - update existing customer
    @PutMapping("{customerId}")
    public Customer updateCustomer(@RequestBody Customer theCustomer, @PathVariable int customerId) {
        Customer tempCustomer = customerService.getCustomer(customerId);
        // throw exception if null
        if (tempCustomer == null)
            throw new NotFoundException("Customer id not found - " + customerId);
        // update customer
        theCustomer.setId(customerId);
        try {
            customerService.updateCustomer(theCustomer);
            return theCustomer;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not update customer");
        }
    }

    // add mapping Delete /customers/{customerId} - delete existing customer
    @DeleteMapping("{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer tempCustomer = customerService.getCustomer(customerId);
        // throw exception if null
        if (tempCustomer == null)
            throw new NotFoundException("Customer id not found - " + customerId);
        try {
            customerService.deleteCustomer(customerId);
            return "Deleted Customer id - " + customerId;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not delete customer");
        }
    }

}
