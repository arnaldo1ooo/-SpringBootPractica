package com.practica.springBootPractica.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.model.Curso;
import com.practica.springBootPractica.model.Topico;

//@Controller
@RestController //Al utilizar RestController en vez de Controller, se evita tener que usar el ResponseBody en cada metodo
public class TopicosController {

	@RequestMapping("/topicos")
	//@ResponseBody //Para que cargue el valor devuelto por el metodo, ya que por default se espera que retorne una pagina
	public List<TopicoDTO> listado() {
		Topico topico = new Topico("Titulo del topico", "Mensaje del topico", new Curso("Spring Boot Parte 1", "Desarrollo"));
		Topico topico2 = new Topico("Titulo del topico 2", "Mensaje del topico 2", new Curso("Spring Boot Parte 1", "Desarrollo 2"));
		Topico topico3 = new Topico("Titulo del topico 3", "Mensaje del topico 3", new Curso("Spring Boot Parte 1", "Desarrollo 3"));
		
		return TopicoDTO.convertir(Arrays.asList(topico, topico2, topico3)); //Retorna una lista de topicos, en este caso manda 3 veces el mismo topico
	}
}
