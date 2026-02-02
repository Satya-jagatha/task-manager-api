package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(InvalidTokenException.class)
	ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setError(HttpStatus.FORBIDDEN.name());
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		
		return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	ResponseEntity<ApiError> handleInvalidCredentialsException(InvalidCredentialsException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setError(HttpStatus.UNAUTHORIZED.name());
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
				
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request)
	{
		String errorMessage = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(err -> err.getField() + ": " + err.getDefaultMessage())
	            .findFirst()
	            .orElse("Validation failed");
		ApiError error = new ApiError();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError(HttpStatus.BAD_REQUEST.name());
		error.setMessage(errorMessage);
		error.setPath(request.getRequestURI());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
