package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onestore.model.CurrentUserSession;

public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer>{
	
	public CurrentUserSession findByUuid(String uuid);

}
