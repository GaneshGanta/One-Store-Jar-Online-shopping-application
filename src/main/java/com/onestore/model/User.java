package com.onestore.model;


import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
	
	
	private String email;
	private String password;
	private String role;
	
	

}
