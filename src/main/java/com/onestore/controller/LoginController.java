package com.onestore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.exception.LoginException;
import com.onestore.model.User;
import com.onestore.serviceImplementation.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoginController {
	
	private LoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> LoginUser( @RequestBody User user ) throws LoginException{
		
         String login = service.LoginYourAccount(user);
		 return new ResponseEntity<String>(login,HttpStatus.OK);
		
	}
	
	@PostMapping("/logout")
	public String logoutCustomer( String key) throws LoginException{
		return service.LogoutYourAccount(key);
		
	}

}
