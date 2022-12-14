package com.onestore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.model.Customer;
import com.onestore.repository.CustomerDao;

@Service
public class Servic {
	@Autowired
	CustomerDao dao;
	
	public void register(Customer cust) {
		Customer obj1 = dao.save(cust);
		System.out.println(obj1.toString());
	}
}
