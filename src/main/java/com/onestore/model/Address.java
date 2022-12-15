package com.onestore.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
    @NotNull
    @NotNull(message = "Enter Street Number Cannot be Null!")
	private String streetNo;
	@NotNull
    private String buildingName;
	
    @NotNull(message = "City Name Cannot be Null!")
	private String city;
	
    @NotNull(message = "State Name Cannot be Null!")
	private String state;
	
    @NotNull(message = "Country Cannot be Null!")
	private String country;
	
    @NotNull(message = "Pincode Cannot be Null")
	private String pincode;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="address")
	private List<Customer> Customer;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="address")
	private List<Order> order;
}
