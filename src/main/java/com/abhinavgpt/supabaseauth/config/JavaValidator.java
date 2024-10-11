package com.abhinavgpt.supabaseauth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class JavaValidator
{

	private final SecretKey key;

	public JavaValidator(@Value("${jwt.secret}") String secret)
	{
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		key = new SecretKeySpec(keyBytes, "HmacSHA256");
	}

	public Claims validateToken(String token)
	{
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}

}