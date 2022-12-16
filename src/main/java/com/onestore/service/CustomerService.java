package com.onestore.service;

import java.util.List;
import com.onestore.exception.CustomerException;
import com.onestore.model.Customer;

	public interface CustomerService {

		Customer addCustomer(Customer cust) throws CustomerException;
		
		Customer updateCustomer(Customer cust, String key) throws CustomerException;
		
		Customer removeCustomer(Integer custId, String key) throws CustomerException;
		
		Customer viewCustomer(Integer custId) throws CustomerException;
		
		List<Customer> viewAllCustomer(String location) throws CustomerException;
			
	}