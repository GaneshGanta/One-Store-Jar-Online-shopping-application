package com.onestore.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.UserSessionDao;
@org.springframework.stereotype.Service
public class Validation {
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	
	public Customer validateLogin(String key) throws LoginException,CustomerException{
		CurrentUserSession checkCustomer = currentuser.findByUuid(key);
		
		if(checkCustomer == null) throw new LoginException("Customer not logged in");
		
		Customer loggedCustomer  = custDao.findById(checkCustomer.getUserId()).orElseThrow(()-> new CustomerException("No Such Customer in Db"));
		
		return loggedCustomer;

		
		             
		             
		             
		
		
		
	}

}
