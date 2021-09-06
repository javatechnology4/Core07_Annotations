package com.spring.mvc;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.model.Customer;
import com.spring.mvc.service.CustomerService;



@RestController
public class HomeController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/allCustomers",method=RequestMethod.GET)
	public  List<Customer> home() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return allCustomers;
	}

	@RequestMapping(method=RequestMethod.POST,value={"/save","/edit/save"})
	public Customer saveCustomer(@RequestBody Customer customer ) {
		customerService.saveCustomer(customer);
		return customer;
		
	}
	@RequestMapping("/edit/{id}")
	public Customer editCustomer(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomer(id);
		return customer;
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteCustomer/{id}")
	public Customer deleteCustomer(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomer(id);
		customerService.deleteCustomer(customer);
		return customer;
	}

}
