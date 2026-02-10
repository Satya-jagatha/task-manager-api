package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.exception.InvalidTokenException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtUtil jwtutil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String authHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		//extract token
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
			try
			{
				username = jwtutil.Extractusername(token);
			}
			catch(Exception ex)
			{
				throw new InvalidTokenException("Token invalid or expired");
			}
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			if(jwtutil.ValidateToken(token))
			{
				
			
			UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(username,null,null);
			authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
			else 
			{
				throw new InvalidTokenException("Token invalide or expired");
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
