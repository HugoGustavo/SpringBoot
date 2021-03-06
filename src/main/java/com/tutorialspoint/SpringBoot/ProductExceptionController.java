package com.tutorialspoint.SpringBoot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<Object> exception(ProductException exception){
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
