package com.onestore.service;


import java.util.List;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.Product;
import com.onestore.model.ProductDto;

public interface ProductService {
	
	
	public List<Product> viewAllProductsService() throws ProductException;

	public Product viewProductByIdSerivce(Integer id) throws ProductException;

	public Product addProductService(Product product,String key) throws ProductException, LoginException, CustomerException;

	public Product updateProductService(Product product,String key) throws ProductException, CustomerException;

	public List<Product> viewProductByCategoryService(String c_name) throws ProductException;

	public Product removeProductService(Integer id,String key) throws ProductException, CustomerException;
	
	
	

}

