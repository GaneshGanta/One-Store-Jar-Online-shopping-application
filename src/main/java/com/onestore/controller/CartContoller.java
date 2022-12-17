package com.onestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.Cart;
import com.onestore.model.Product;
import com.onestore.model.ProductDto;
import com.onestore.service.CartServices;

@RestController
@RequestMapping(value="/cart")
public class CartContoller {
	
	@Autowired
	public CartServices Cservice;
	
	@PostMapping("/add/{pid}/{key}")
	public ResponseEntity<Cart>addProductToCartHandler(@PathVariable("pid") Integer pid,@PathVariable("key") String key ) throws LoginException, CustomerException, ProductException
	{
		Cart updatedCart = Cservice.addProductToCart(pid,  key);
		
		return new ResponseEntity<Cart>(updatedCart,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/remove/{pid}/{key}/{q}")//removeproductFromCart(Integer pid, String key, Integer cid,Integer quantity)
	public ResponseEntity<ProductDto>removeProductFromCartHandler(@PathVariable("pid") Integer pid,@PathVariable("key") String key ,@PathVariable("q") Integer quantity) throws LoginException, CustomerException
	{
		ProductDto p = Cservice.removeproductFromCart(pid, key,  quantity);
		
		return new ResponseEntity<ProductDto>(p,HttpStatus.ACCEPTED);
	}
	
	

}
