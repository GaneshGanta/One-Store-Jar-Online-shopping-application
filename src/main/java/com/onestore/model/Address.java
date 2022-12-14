package com.onestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
	
	
	
	private int addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String country;
	private String pincode;
	
	

}
