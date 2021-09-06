package com.spring.mvc.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {
	private Long id;
	@Size(min=3,max=20, message="{customer.name.empty}")
	@NotEmpty( message="{customer.name.empty}")
	private String name;
	@Email(message="{customer.email.invalid}")
	private String email;
	@NotEmpty( message="{customer.address.empty}")
	private String address;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
