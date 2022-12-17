package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onestore.model.Product;
import com.onestore.model.ProductDto;

public interface ProductDtoDao extends JpaRepository<ProductDto, Integer> {

}
