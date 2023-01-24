package com.hazzum.storefront.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.storefront.DAO.CustomerRepository;
import com.hazzum.storefront.entity.Customer;
import com.hazzum.storefront.rest.exceptionHandler.exceptionHandler.NotFoundException;

@Component
public class CustomerServiceImpl implements CustomerService {
private CustomerRepository CustomerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		CustomerRepository = theCustomerRepository;
	}
	
	@Override
	public List<Customer> index() {
		return CustomerRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Customer getCustomer(int theId) {
		Optional<Customer> result = CustomerRepository.findById(theId);
		
		Customer theCustomer = null;
		
		if (result.isPresent()) {
			theCustomer = result.get();
			return theCustomer;
		}
		else {
			throw new NotFoundException("Customer not found id: " + theId);
		}
	}

	@Override
	public Customer createCustomer(Customer theCustomer) {
		return CustomerRepository.save(theCustomer);
	}
	
	@Override
	public Customer updateCustomer(Customer theCustomer) {
		return CustomerRepository.save(theCustomer);
	}

	@Override
	public Customer deleteCustomer(int theId) {
		CustomerRepository.deleteById(theId);
		return null;
	}

}
