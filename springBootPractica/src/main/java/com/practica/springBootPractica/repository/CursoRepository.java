package com.practica.springBootPractica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.springBootPractica.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	Optional<Curso> findByNombre(String nombreCurso);
}
