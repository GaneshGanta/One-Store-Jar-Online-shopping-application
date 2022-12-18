package com.onestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.Address;
import com.onestore.service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Delegate;


@RestController
@RequestMapping("/address")
public class AddressController {
	

@Autowired
AddressService addService;
	
	@PostMapping("/add/{key}")
	public ResponseEntity<Address> addAddres(@RequestBody Address address,@PathVariable("key") String key) throws LoginException, CustomerException{
		Address new_add = addService.addAddres(address, key);
		return new ResponseEntity<Address>(new_add,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<Address> updateAddress(Address address,@PathVariable("key") String key) throws LoginException, CustomerException{
		Address updated_add = addService.updateAddress(address, key);
		return new ResponseEntity<Address>(updated_add,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{key}")
	public ResponseEntity<Address> removeAddress(@PathVariable("key") String key) throws LoginException, CustomerException {
		Address removed_add = addService.removeAddress(key);
		return new ResponseEntity<Address>(removed_add,HttpStatus.OK);
	}
	@GetMapping("/viewAll")
	public ResponseEntity<Address> viewAllAddress(){
		return new ResponseEntity<Address>((Address) addService.viewAllAddress(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Address> addAddress(@PathVariable("id") Integer userId) throws LoginException, CustomerException {
		Address removed_add = addService.viewAddressByUserId(userId);
		return new ResponseEntity<Address>(removed_add,HttpStatus.ACCEPTED);
	}
}
