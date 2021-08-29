package com.practica.springBootPractica.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.service.TopicoService;

//@Controller
@RestController //Al utilizar RestController en vez de Controller, se evita tener que usar el ResponseBody en cada metodo
public class TopicosController {

	@Autowired
	private TopicoService topicoService;
	
	@RequestMapping("/topicos")
	//@ResponseBody //Para que cargue el valor devuelto por el metodo, ya que por default se espera que retorne una pagina
	public List<TopicoDTO> listado(String cursoNombre) {
		
		return topicoService.listado(cursoNombre);
	}
}
