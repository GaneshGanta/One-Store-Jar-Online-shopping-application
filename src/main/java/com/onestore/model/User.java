package com.onestore.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;
@MappedSuperclass
@Data
public class User {

	private String email;
	private String password;
	private String role;
	
}
