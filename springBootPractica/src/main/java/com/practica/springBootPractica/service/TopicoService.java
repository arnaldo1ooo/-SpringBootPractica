package com.practica.springBootPractica.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.controller.dto.TopicoDetalleDTO;
import com.practica.springBootPractica.controller.form.ActualizaTopicoForm;
import com.practica.springBootPractica.controller.form.TopicoForm;
import com.practica.springBootPractica.exception.RecursoNoEncontradoException;
import com.practica.springBootPractica.model.Curso;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.model.Usuario;
import com.practica.springBootPractica.repository.CursoRepository;
import com.practica.springBootPractica.repository.TopicoRepository;
import com.practica.springBootPractica.repository.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@Cacheable(value = "listadoDeTopicos")
	public Page<TopicoDTO> listado(String cursoNombre, Pageable pageable) {
		Page<Topico> resultado;
		
		if (cursoNombre == null) {
			resultado = topicoRepository.findAll(pageable);
		}
		else {
			resultado = topicoRepository.findByCurso_Nombre(cursoNombre, pageable);
		}
		
		return TopicoDTO.convertir(resultado);
	}

	@CacheEvict(value = "listadoDeTopicos", allEntries = true) //Limpia la cache si el metodo es ejecutado
	public Topico registrar(TopicoForm topicoForm) {
		Optional<Usuario> usuario= usuarioRepository.findById(topicoForm.getIdUsuario());
		Optional<Curso> curso = cursoRepository.findByNombre(topicoForm.getCursoNombre());
	
		Topico topico = topicoForm.convertir(usuario, curso);
		
		return topicoRepository.save(topico);
	}

	public TopicoDetalleDTO detalle(Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (!topico.isPresent()) {
			throw new RecursoNoEncontradoException(String
					.format("El topico de id %s no fue encontrado", id));
		} 
		
		return new TopicoDetalleDTO(topico.get());
	}

	@Transactional
	@CacheEvict(value = "listadoDeTopicos", allEntries = true) //Limpia la cache si el metodo es ejecutado
	public Topico actualizar(Long id, @Valid ActualizaTopicoForm actualizaTopicoForm) {
		Optional<Topico> optTopico = topicoRepository.findById(id);
		
		if (!optTopico.isPresent()) {
			throw new RecursoNoEncontradoException(String
					.format("El topico de id %s no fue encontrado", id));
		}
		
		Topico topico = optTopico.get();	
		topico.setTitulo(actualizaTopicoForm.getTitulo());
		topico.setMensaje(actualizaTopicoForm.getMensaje());
		
		return topico;
	}

	@CacheEvict(value = "listadoDeTopicos", allEntries = true) //Limpia la cache si el metodo es ejecutado
	public void borrar(Long id) {
		Optional<Topico> optTopico = topicoRepository.findById(id);
		
		if (!optTopico.isPresent()) {
			throw new RecursoNoEncontradoException(String
					.format("El topico de id %s no fue encontrado", id));
		}
		
		topicoRepository.deleteById(id);
	}
	
	
	
}
