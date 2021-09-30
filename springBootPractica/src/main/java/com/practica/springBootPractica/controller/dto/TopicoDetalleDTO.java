package com.practica.springBootPractica.controller.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.practica.springBootPractica.model.Respuesta;
import com.practica.springBootPractica.model.StatusTopico;
import com.practica.springBootPractica.model.Topico;

import java.time.LocalDateTime;

public class TopicoDetalleDTO {

	private Long id;
	
	private String titulo;
	
	private String mensaje;
	
	private LocalDateTime fechaCreacion;
	
	private String autor;
	
	private StatusTopico status;
	
	private List<RespuestaDTO> respuestas;
	
	
	public TopicoDetalleDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensaje = topico.getMensaje();
		this.fechaCreacion= topico.getFechaCreacion();
		this.autor = topico.getAutor().getNombre();
		this.status = topico.getStatus();
		
		if (topico.getRespuestas() != null && !topico.getRespuestas().isEmpty()) {
			this.respuestas = topico.getRespuestas().stream().map(RespuestaDTO::new).collect(Collectors.toList());
		}
	}

	
	public String getTitulo() {
		return titulo;
	}


	public Long getId() {
		return id;
	}


	public String getMensaje() {
		return mensaje;
	}


	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}


	public String getAutor() {
		return autor;
	}


	public StatusTopico getStatus() {
		return status;
	}


	public List<RespuestaDTO> getRespuestas() {
		return respuestas;
	}
	
	
	
}
