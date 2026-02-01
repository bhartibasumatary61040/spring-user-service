package com.springuserservice.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springuserservice.demo.models.APIError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<APIError>  handleRuntimeException(ResourceNotFoundException ex, HttpServletRequest request) {
		
		HttpStatus status= HttpStatus.NOT_FOUND;
		APIError apiError = new APIError(
				LocalDateTime.now(),
				status.value(),
				status.name(),
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		
		
	}

}
