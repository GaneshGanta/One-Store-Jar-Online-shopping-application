package com.onestore.service;


import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;

import com.onestore.model.Product;

public interface CartServices {

    public Product removeproductFromCart(Integer pid,String key ,Integer cid,Integer quantity)  throws CustomerException, LoginException;
	
	public Product updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws Exception;
	
	public String addProductToCart(Integer pid, Integer custId, String key) throws CustomerException,LoginException;
	
	}

	

