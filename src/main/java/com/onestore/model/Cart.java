package com.onestore.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
		
	@OneToMany(cascade = CascadeType.ALL)
	private java.util.List<Product> products;
	
	/*
	 
	 * add customer one to one.
	 * 
	 */
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public Cart(java.util.List<Product> products) {
		super();
		this.products = products;
	}
	
	
	
	
}
