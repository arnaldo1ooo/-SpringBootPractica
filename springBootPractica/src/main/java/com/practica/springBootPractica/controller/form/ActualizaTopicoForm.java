package com.practica.springBootPractica.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ActualizaTopicoForm {
	
	@NotEmpty @Size(min= 5)
	private String titulo;
	
	@NotEmpty @Size(min= 5)
	private String mensaje;

	
	
	
	public String getTitulo() {
		return titulo;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	
}
