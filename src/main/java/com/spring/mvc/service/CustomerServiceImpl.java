package com.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.database.CustomerDao;
import com.spring.mvc.model.Customer;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private EmailService emailService;

	@Override
	public void saveCustomer(Customer customer) {
		emailService.sendMail(customer.getEmail(), "Subject");
		customerDao.saveCustomer(customer);
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return customerDao.getCustomer(id);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomer(customer);
	}

}
