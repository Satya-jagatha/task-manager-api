package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(TaskNotFoundException.class)
	ResponseEntity<ApiError> handleTaskNotFoundException(TaskNotFoundException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError(HttpStatus.NOT_FOUND.name());
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiError> handleGenericExcpetion(Exception ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setMessage("An unexpected error occurred");
		error.setPath(request.getRequestURI());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
