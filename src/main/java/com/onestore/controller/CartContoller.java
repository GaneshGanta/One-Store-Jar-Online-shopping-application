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
	
	@DeleteMapping("/remove/{pDtoId}/{key}")
	public ResponseEntity<ProductDto>removeProductFromCartHandler(@PathVariable("pDtoId") Integer pDtoId,@PathVariable("key") String key) throws LoginException, CustomerException
	{
		ProductDto product = Cservice.removeproductFromCart(pDtoId, key);
		
		return new ResponseEntity<ProductDto>(product,HttpStatus.ACCEPTED);
	}
	
	
	
	
	@PutMapping("/update/{pDtoId}/{q}/{key}")
	public ResponseEntity<ProductDto>updateProductQuantityHandler(@PathVariable("pDtoId")Integer pDtoId,@PathVariable("q") Integer quantity,@PathVariable("key") String key) throws LoginException, CustomerException{
		
		           ProductDto product =Cservice.updateProductQuantity(pDtoId, quantity, key);
		
		return new ResponseEntity<ProductDto>(product,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{key}") 
	public ResponseEntity<List<ProductDto>>viewAllProducts(@PathVariable("key") String key) throws LoginException, CustomerException{
		         List<ProductDto> productlist = Cservice.viewAllProductsFromCart(key);
		         
		return new ResponseEntity<List<ProductDto>>(productlist,HttpStatus.OK);
	}
	
	@GetMapping("/total/{key}")
	
	public ResponseEntity<Integer> CartContoller(@PathVariable("key") String key) throws LoginException, CustomerException, ProductException{
		double cartTotal =  Cservice.cartTotal(key);
		return new  ResponseEntity<Integer>((int) cartTotal,HttpStatus.OK);
	}

}
