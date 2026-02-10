package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
	@Autowired
	private JwtUtil jwtutil;
	
	private final String VALID_USERNAME = "admin";
	private final String VALID_PASSWORD = "admin123";
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request)
	{
		if(!request.getUsername().equals(VALID_USERNAME) || !request.getPassword().equals(VALID_PASSWORD))
		{
			throw new InvalidCredentialsException("Username and password are incorrect");
		}
		
		String token = jwtutil.GenerateToken(request.getUsername());
		
		return new LoginResponse(token);
	}
	
	
}
