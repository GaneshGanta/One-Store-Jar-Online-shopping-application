package com.onestore.service;

import com.onestore.exception.CartException;
import com.onestore.model.Cart;
import com.onestore.model.Product;

public interface CartServices {

    public Product removeproductFromCart(Integer pid,String key ,Integer cid) throws Exception;
	
	public Product updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws Exception;
	
	public String addProductToCart(Integer pid, Integer custId, String key) throws CustomerException,LoginException;
	
	}

	

