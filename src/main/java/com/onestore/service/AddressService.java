package com.onestore.service;

import com.onestore.model.Address;

public interface AddressService {
	public Address addAddres(Address address);
	public Address updateAddress(Address address);
	public Address removeAddress(Address address);
	public Address viewAllAddressByUserId(Integer userId);
	public Address viewAddress(Integer addressId);

}
