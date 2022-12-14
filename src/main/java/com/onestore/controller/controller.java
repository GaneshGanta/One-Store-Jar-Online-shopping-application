package com.onestore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.model.Customer;
import com.onestore.repository.CustomerDao;
import com.onestore.service.Servic;

@RestController
public class controller {
	@Autowired
	Servic s;
	@PostMapping("/customer")
	public String regstCUstomer(@RequestBody Customer customer) {
		s.register(customer);
		return "Working fine";
	}
	@GetMapping("/customer")
	public String get() {
		return  "hi";
	}
	@GetMapping("/customer/data/{id}")
	public String getDat(@PathVariable("id") Integer id) {
		return  "hi";
	}

}
