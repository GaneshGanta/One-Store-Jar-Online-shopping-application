package com.onestore.model;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@MappedSuperclass
@Setter
@Getter
@ToString
public class User {

	private String email;
	private String password;
	private String role;
	
}
