package com.dogametal.springbootmongodb.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dogametal.springbootmongodb.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND; //Status = 404 
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Search not found.", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
}
