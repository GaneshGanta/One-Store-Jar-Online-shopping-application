package com.onestore.service;

import java.util.List;
import com.onestore.exception.ProductException;
import com.onestore.model.Product;

public interface ProductService {
	
	
	public List<Product> viewAllProductsService() throws ProductException;

	public Product viewProductByIdSerivce(Integer id) throws ProductException;

	public Product addProductService(Product product) throws ProductException;

	public Product updateProductService(Product product) throws ProductException;

	public List<Product> viewProductByCategoryService(String c_name) throws ProductException;

	public Product removeProductService(Integer id) throws ProductException;
	
	
	

}
