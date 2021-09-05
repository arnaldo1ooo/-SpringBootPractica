package com.practica.springBootPractica.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.controller.dto.TopicoDetalleDTO;
import com.practica.springBootPractica.controller.form.ActualizaTopicoForm;
import com.practica.springBootPractica.controller.form.TopicoForm;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoService topicoService;
	
	
	
	@GetMapping
	public ResponseEntity<List<TopicoDTO>> listado(String cursoNombre) {
		return ResponseEntity.ok(topicoService.listado(cursoNombre));
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> registrar(@Valid @RequestBody TopicoForm topicoForm, 
			UriComponentsBuilder uriComponentsBuilder) { //@Valid ejecuta el beanValidation
		Topico topico = topicoService.registrar(topicoForm);
		
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalleDTO> detalle(@PathVariable Long id){ //@PathVariable indica que el parametro id va a provenir de la url
		TopicoDetalleDTO detalle = topicoService.detalle(id);
		
		return ResponseEntity.ok(detalle);
	}
	
	@PutMapping("/{id}")  //El PUT actualiza todos los campos del registro, el cambio el PET actualiza solo los campos puntuales
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody  @Valid ActualizaTopicoForm actualizaTopicoForm) {
		Topico topico = topicoService.actualizar(id, actualizaTopicoForm);
		
		return ResponseEntity.ok(new TopicoDetalleDTO(topico));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity borrar(@PathVariable Long id) {
		topicoService.borrar(id);
		
		return ResponseEntity.ok().build();
	}
	
}
