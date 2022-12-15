package com.onestore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	//@NotNull (message = "Product Name is Mandatory!")
	private String productName;
	//@NotNull (message = "Price is Mandatory!")
	private double price;
	
	@NotNull (message = "Color is Mandatory!")
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	private int quantity;
	private String url;
	private String category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> order;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Category category;	
}
