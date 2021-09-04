package com.practica.springBootPractica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.springBootPractica.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	
	
}
