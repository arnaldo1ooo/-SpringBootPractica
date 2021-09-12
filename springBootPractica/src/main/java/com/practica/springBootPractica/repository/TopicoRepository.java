package com.practica.springBootPractica.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practica.springBootPractica.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	List<Topico> findByTitulo(String titulo);
	
	//Busca por nombre del curso de los topicos, la sintaxis es asi (findBy + Relacion + _ + Campo)
	Page<Topico> findByCurso_Nombre(String cursoNombre, Pageable pageable);
	
}
