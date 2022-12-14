package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onestore.model.User;


public interface UserDao extends JpaRepository<User, String>{

}
