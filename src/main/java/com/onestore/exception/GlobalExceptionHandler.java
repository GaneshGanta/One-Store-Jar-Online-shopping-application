package com.onestore.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException pe, WebRequest req){
		
		MyErrorDetails med = new MyErrorDetails(LocalDateTime.now(), pe.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(med,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException pe, WebRequest req){
		
		MyErrorDetails med=new MyErrorDetails(LocalDateTime.now(), pe.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(med,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException pe, WebRequest req){
		
		MyErrorDetails med = new MyErrorDetails(LocalDateTime.now(), pe.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(med,HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> generalExceptionHandler(Exception pe, WebRequest req){
		
		MyErrorDetails med = new MyErrorDetails(LocalDateTime.now(), pe.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(med,HttpStatus.NOT_FOUND);
		
	}
	
	
	
	

}
