package com.onestore.service;

import com.onestore.model.Product;

public class CartServicesImpl implements CartServices{

	@Override
	public Product removeproductFromCart(Integer pid, String key, Integer cid) throws CustomerException, LoginException{
		  
		
		
		return null;
	}

	@Override
	public Product updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProductToCart(Integer pid, Integer custId, String key) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
