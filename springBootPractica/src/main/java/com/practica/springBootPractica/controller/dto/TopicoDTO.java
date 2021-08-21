package com.practica.springBootPractica.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.practica.springBootPractica.model.Topico;

public class TopicoDTO {
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
	
	public static List<TopicoDTO> convertir(List<Topico> topicos) {
		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList()); //Convierte lista Topico a lista TopicoDTO, por que si se devuelve directamente el dominio o model Topico, estamos devolviendo todos sus campos
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
