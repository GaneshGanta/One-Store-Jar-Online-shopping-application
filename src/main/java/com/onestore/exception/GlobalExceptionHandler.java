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
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> myEhandler(CustomerException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> orderExceptionHandler(OrderException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myEhandler(LoginException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myEhandler(MethodArgumentNotValidException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myEhandler(Exception e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myEhandler(NoHandlerFoundException e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
}
