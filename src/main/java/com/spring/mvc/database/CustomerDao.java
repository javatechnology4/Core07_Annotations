package com.spring.mvc.database;

import java.util.List;

import com.spring.mvc.model.Customer;

public interface CustomerDao {
	public void saveCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer getCustomer(Long id);
	public void deleteCustomer(Customer customer);

}
