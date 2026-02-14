package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil 
{
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;
	
	public String GenerateToken(String username)
	{
		Key key = Keys.hmacShaKeyFor(secret.getBytes());
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
				
	}
	
	public Claims getClaims(String token)
	{
		Key key = Keys.hmacShaKeyFor(secret.getBytes());
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String Extractusername(String token)
	{
		return getClaims(token).getSubject();
	}
	
	public boolean ValidateToken(String token)
	{
		try
		{
			getClaims(token);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	

}
