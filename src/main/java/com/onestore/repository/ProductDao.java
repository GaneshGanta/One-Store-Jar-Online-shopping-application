package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onestore.model.Order;
import com.onestore.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	

}
