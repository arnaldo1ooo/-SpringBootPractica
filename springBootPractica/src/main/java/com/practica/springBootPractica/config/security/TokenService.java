package com.practica.springBootPractica.config.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.practica.springBootPractica.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

	@Value("${forum.jwt.expirationTime}")
	private Long expirationTime;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.issuer}")
	private String issuer;
	
	public String generarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		LocalDateTime fechaActual = LocalDateTime.now();
		LocalDateTime fechaExpiracion = fechaActual.plusMinutes(expirationTime);
		
		return Jwts.builder()
				.setIssuer(issuer)
				.setSubject(usuario.getId().toString())
				.setIssuedAt(Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(fechaExpiracion.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Optional<Jws<Claims>> getTokenInfo(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			
			return Optional.of(claims);
		} catch (Exception e) {
			//e.printStackTrace();
			return Optional.empty();
		} 
	}
	
	
}
