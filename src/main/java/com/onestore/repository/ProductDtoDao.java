package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.onestore.model.ProductDto;

@Repository
public interface ProductDtoDao extends JpaRepository<ProductDto, Integer> {

}
