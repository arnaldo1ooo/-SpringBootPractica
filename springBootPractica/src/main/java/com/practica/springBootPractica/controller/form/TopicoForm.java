package com.practica.springBootPractica.controller.form;

import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.practica.springBootPractica.model.Curso;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.model.Usuario;

//Clases tipo Formulario envian datos a la bd

public class TopicoForm {
	
	@NotBlank @Size(min = 5)
	private String titulo;
	
	@NotBlank @Size(min = 5)
	private String mensaje;
	
	@NotNull
	private Long idUsuario;
	
	@NotBlank @Size(min = 5)
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
