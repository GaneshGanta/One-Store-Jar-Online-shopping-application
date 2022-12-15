package com.onestore.exception;

import java.time.LocalDateTime;

public class MyErrorDetails {
	
	
	private LocalDateTime ldt;
	private String message;
	private String description;
	
	
	@Override
	public String toString() {
		return "MyErrorDetails [ldt=" + ldt + ", message=" + message + ", description=" + description + "]";
	}
	
	
	public MyErrorDetails(LocalDateTime ldt, String message, String description) {
		super();
		this.ldt = ldt;
		this.message = message;
		this.description = description;
	}
	
	
	public MyErrorDetails() {
		super();
	}
	

}
