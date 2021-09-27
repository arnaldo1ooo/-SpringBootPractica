package com.practica.springBootPractica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.springBootPractica.config.security.AutenticacionService;
import com.practica.springBootPractica.controller.dto.TokenDTO;
import com.practica.springBootPractica.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {
	@Autowired
	private AutenticacionService autenticacionService;
	
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody @Valid LoginForm loginForm){
		try {
			String token = autenticacionService.autenticacionConToken(loginForm);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
