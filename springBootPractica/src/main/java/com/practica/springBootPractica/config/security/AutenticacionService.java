package com.practica.springBootPractica.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practica.springBootPractica.model.Usuario;
import com.practica.springBootPractica.repository.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		return usuario.orElseThrow(() -> new UsernameNotFoundException("Datos invalidos"));
		
	}
	
}
