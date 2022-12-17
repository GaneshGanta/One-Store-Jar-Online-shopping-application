package com.onestore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@NotNull (message = "Product Name is Mandatory!")
	private String productName;
	@NotNull (message = "Price is Mandatory!")
	private double price;
	@NotNull (message = "Color is Mandatory!")
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	private int quantity;
	private String url;
    private String category;
//    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "cartId"))
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Cart cart;
	//mapped by should be in product entity not in order
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "productList")
	@JsonIgnore
	private List<Order> order;
	
	

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", color="
				+ color + ", dimension=" + dimension + ", specification=" + specification + ", manufacturer="
				+ manufacturer + ", quantity=" + quantity + ", url=" + url + "]";
	}	
}
