package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onestore.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

}
