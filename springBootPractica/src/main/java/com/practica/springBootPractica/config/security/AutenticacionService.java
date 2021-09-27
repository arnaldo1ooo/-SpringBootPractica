package com.practica.springBootPractica.config.security;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practica.springBootPractica.controller.form.LoginForm;
import com.practica.springBootPractica.model.Usuario;
import com.practica.springBootPractica.repository.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		return usuario.orElseThrow(() -> new UsernameNotFoundException("Datos invalidos"));
		
	}

	public String autenticacionConToken(@Valid LoginForm loginForm) {
		Authentication datosLogin = loginForm.convertir();
		Authentication authentication = authManager.authenticate(datosLogin);
		
		return tokenService.generarToken(authentication);
	}
	
}
