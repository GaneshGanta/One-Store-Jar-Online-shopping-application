package com.onestore.serviceImplementation;

import java.lang.StackWalker.Option;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.exception.ProductException;
import com.onestore.model.Cart;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.Product;
import com.onestore.model.ProductDto;
import com.onestore.repository.CartDao;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.ProductDao;
import com.onestore.repository.ProductDtoDao;
import com.onestore.repository.UserSessionDao;
import com.onestore.service.CartServices;


@Service
public class CartServicesImpl implements CartServices{

	@Autowired
	private ProductDtoDao productDao;
	
	@Autowired
	private CartDao cartD;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private Validation valid;
	
	@Override
	public ProductDto removeproductFromCart(Integer pid, String key,Integer quantity) throws CustomerException, LoginException{
		  
		return null;	
		

	}

	
	@Override   ///this method is not working only other are working......................
	public ProductDto removeproductFromCart(Integer  pDtoId, String key) throws CustomerException, LoginException,CartException, ProductException{
		
	return null;
	}
	
	
	
	
	
	

	@Override

	public ProductDto updateProductQuantity(Integer pDtoId, Integer quantity, String key) throws CustomerException,LoginException {
		
		
		return null;

	@Override
	public Cart addProductToCart(Integer pid,  String key) throws CustomerException, LoginException, ProductException {
		    
		return null;
		



	@Override
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException, ProductException {
			
		return null;
			
			
		
	}



	@Override
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException{
		
		return null;
	}


}
