package com.practica.springBootPractica.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.practica.springBootPractica.model.Topico;

//Clases tipo DTO reciben datos de la bd

public class TopicoDTO {	//Data Tranfer Object, aca se puede especificar que campos de la entidad se quiere obtener en la consulta
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion;
	
	//Constructor
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensaje = topico.getMensaje();
		this.fechaCreacion = topico.getFechaCreacion();
	}
	
	public static Page<TopicoDTO> convertir(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}
	
	
	
	//Getters
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
}
