package com.onestore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private List<Product> productList;
}
