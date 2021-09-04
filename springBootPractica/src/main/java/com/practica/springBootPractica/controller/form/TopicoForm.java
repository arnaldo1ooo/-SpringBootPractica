package com.practica.springBootPractica.controller.form;

import java.util.Optional;

import com.practica.springBootPractica.model.Curso;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.model.Usuario;

//Clases tipo Form envian datos a la bd

public class TopicoForm {
	private String titulo;
	
	private String mensaje;
	
	private Long idUsuario;
	
	private String cursoNombre;

	
	
	
	//Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCursoNombre() {
		return cursoNombre;
	}

	public void setCursoNombre(String cursoNombre) {
		this.cursoNombre = cursoNombre;
	}

	public Topico convertir(Optional<Usuario> usuario, Optional<Curso> curso) {
		return new Topico(titulo, mensaje, usuario, curso);
	}

}
