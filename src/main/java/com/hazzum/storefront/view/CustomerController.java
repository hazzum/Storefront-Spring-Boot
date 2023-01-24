package com.hazzum.storefront.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hazzum.storefront.entity.Customer;
import com.hazzum.storefront.service.CustomerService;

@Controller
@RequestMapping("/view/customers")
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from db
		List<Customer> theCustomers = customerService.index();
		
		// add to the spring model
		theModel.addAttribute("customers", theCustomers);
		
		return "customers/list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customers/customer-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form
		return "customers/customer-form";			
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer
		customerService.createCustomer(theCustomer);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/view/customers/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		// redirect to /customers/list
		return "redirect:/view/customers/list";
		
	}
}


















