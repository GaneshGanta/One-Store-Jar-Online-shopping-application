package com.onestore.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onestore.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	
	public List<Order> findByOrderDate(LocalDate date); 
}
