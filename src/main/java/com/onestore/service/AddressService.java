package com.onestore.service;

import java.util.List;

import com.onestore.exception.CustomerException;
import com.onestore.exception.LoginException;
import com.onestore.model.Address;

public interface AddressService {
	public Address addAddres(Address address,String key) throws LoginException, CustomerException;
	public Address updateAddress(Address address,String key) throws LoginException, CustomerException;
	public Address removeAddress( String key) throws LoginException, CustomerException;
	public List<Address> viewAllAddress();
	public Address viewAddressByUserId(Integer userId) throws CustomerException;

}
