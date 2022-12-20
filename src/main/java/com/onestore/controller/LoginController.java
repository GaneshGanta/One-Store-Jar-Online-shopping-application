package com.onestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.model.User;
import com.onestore.serviceImplementation.LoginService;



@RestController
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> LoginUser( @RequestBody User user ){
		
		if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equalsIgnoreCase("admin")) {
			
			user.setRole("admin");
			
			String login = service.LoginYourAccount(user);
			 return new ResponseEntity<String>(login,HttpStatus.OK);
			
		}else {
			
			user.setRole("customer");
			
			 String login = service.LoginYourAccount(user);
			 return new ResponseEntity<String>(login,HttpStatus.OK);
			
		}
		
	}
	
	@DeleteMapping("/logout/{k}")
	public String logoutCustomer( @PathVariable("k") String key){
		return service.LogoutYourAccount(key);
		
	}

}
