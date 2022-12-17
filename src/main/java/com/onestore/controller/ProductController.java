package com.onestore.controller;



import java.util.List;

import javax.validation.Valid;

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

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.Product;
import com.onestore.service.ProductService;


@RestController

public class ProductController {
	
	
	
	@Autowired
	private ProductService productService;
	
	
	
	@GetMapping("/product/viewall")
	public ResponseEntity<List<Product>> viewAllProductsHandler() throws ProductException{
		
		return new ResponseEntity<List<Product>>(productService.viewAllProductsService(), HttpStatus.CREATED);
		
	}
	 
	
	@GetMapping("/product/viewproduct/{Id}")
	public ResponseEntity<Product> viewProductHandler(@PathVariable Integer Id) throws ProductException{
		
		Product product = productService.viewProductByIdSerivce(Id);
		
		return new ResponseEntity<Product>(product, HttpStatus.FOUND);
		
	}
	
	
	@PostMapping("/product/add/{key}")
	public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product,@PathVariable String key) throws ProductException, LoginException, CustomerException {
		
		System.out.println(product);
		
		Product addedProduct = productService.addProductService(product,key);
		
		return new ResponseEntity<Product>(addedProduct, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/product/update/{key}")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product,@PathVariable String key) throws ProductException, CustomerException{
		
		Product updated = productService.updateProductService(product,key);
		
		return new ResponseEntity<Product>(updated, HttpStatus.OK);
	}
	
	
	@GetMapping("/product/viewbycategory")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@RequestParam(value="category") String cname) throws ProductException{
		
		List<Product> allProducts = productService.viewProductByCategoryService(cname);
		
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.FOUND);
	}
	
	
	@DeleteMapping("/product/remove/{Id}/{key}")
	public ResponseEntity<Product> removeProductHandler(@PathVariable Integer Id,@PathVariable String key) throws ProductException, CustomerException {
		
		Product product = productService.removeProductService(Id,key);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	

}
