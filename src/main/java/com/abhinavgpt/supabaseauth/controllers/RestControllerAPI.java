package com.abhinavgpt.supabaseauth.controllers;

import com.abhinavgpt.supabaseauth.config.JavaValidator;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerAPI
{

	private final JavaValidator jwtValidator;

	public RestControllerAPI(JavaValidator jwtValidator)
	{
		this.jwtValidator = jwtValidator;
	}

	@GetMapping("/protected")
	public String getUserClaims(@RequestHeader("Authorization") String authHeader)
	{
		String token = authHeader.replace("Bearer ", "");
		Claims claims = jwtValidator.validateToken(token);
		String userId = claims.getSubject();
		return "Hello, user " + userId;
	}
}