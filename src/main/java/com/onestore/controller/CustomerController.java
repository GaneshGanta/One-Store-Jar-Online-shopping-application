package com.onestore.controller;


import java.util.List;
import javax.validation.Valid;
import com.onestore.exception.CustomerException;
import com.onestore.model.Customer;
import com.onestore.serviceImplementation.CustomerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceImpl cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer cust) throws CustomerException{
		System.out.println(cust);
		
		Customer saveCustomer = cService.addCustomer(cust);
		
		return new ResponseEntity<Customer>(saveCustomer, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/customer/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer cust, @PathVariable String key) throws CustomerException{
		
		Customer updateCustomer = cService.updateCustomer(cust, key);
		
		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/customers/{custId}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable Integer custId, @RequestParam("key") String key) throws CustomerException{
		
		Customer removeCustomer = cService.removeCustomer(custId, key);
		
		return new ResponseEntity<Customer>(removeCustomer, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/customer/{custId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable Integer custId) throws CustomerException{
		
		Customer viewCustomer = cService.viewCustomer(custId);
		
		return new ResponseEntity<Customer>(viewCustomer, HttpStatus.OK);
	}
	
	
	@GetMapping("/customers/{location}")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@PathVariable String location )throws CustomerException{
		
		List<Customer> viewAllCustomer = cService.viewAllCustomer(location);
		
		return new ResponseEntity<List<Customer>>(viewAllCustomer, HttpStatus.OK);
	}	
	
}