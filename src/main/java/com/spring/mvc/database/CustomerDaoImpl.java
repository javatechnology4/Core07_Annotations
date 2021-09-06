package com.spring.mvc.database;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.mvc.model.Customer;
@Repository
public class CustomerDaoImpl implements CustomerDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveCustomer(Customer customer) {
		Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
		hibernateTemplate.saveOrUpdate(customer);
		transaction.commit();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
		List<Customer>listOfCustomers= (List<Customer>) hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Customer").list();
		transaction.commit();
		return listOfCustomers;
	}

	@Override
	public Customer getCustomer(Long id) {
		Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
		 Customer customer = hibernateTemplate.get(Customer.class, id);
		 transaction.commit();
		 return customer;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
		hibernateTemplate.delete(customer);
		transaction.commit();
	}

}
