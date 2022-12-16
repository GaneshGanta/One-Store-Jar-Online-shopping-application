package com.onestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.Product;
import com.onestore.service.CartServices;

@RestController
@RequestMapping(value="/cart")
public class CartContoller {
	
	@Autowired
	public CartServices Cservice;
	
	@PutMapping("/addproducttocart/pid/cid/key")//
	public ResponseEntity<String>addProductToCartHandler(@PathVariable("pid") Integer pid,@PathVariable("cid") Integer custid,@PathVariable("key") String key ) throws LoginException, CustomerException
	{
		String p = Cservice.addProductToCart(pid, custid, key);
		
		return new ResponseEntity<String>(p,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/removeproductfromcart/pid/key/cid/q")//removeproductFromCart(Integer pid, String key, Integer cid,Integer quantity)
	public ResponseEntity<Product>removeProductFromCartHandler(@PathVariable("pid") Integer pid,@PathVariable("key") String key ,@PathVariable("cid") Integer custid,@PathVariable("q") Integer quantity) throws LoginException, CustomerException
	{
		Product p = Cservice.removeproductFromCart(pid, key, custid, quantity);
		
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	

}
