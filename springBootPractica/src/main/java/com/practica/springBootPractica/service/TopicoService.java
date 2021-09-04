package com.practica.springBootPractica.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.controller.form.TopicoForm;
import com.practica.springBootPractica.model.Curso;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.model.Usuario;
import com.practica.springBootPractica.repository.CursoRepository;
import com.practica.springBootPractica.repository.TopicoRepository;
import com.practica.springBootPractica.repository.UsuarioRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	public List<TopicoDTO> listado(String cursoNombre) {
		List<Topico> resultado;
		
		if (cursoNombre == null) {
			resultado = topicoRepository.findAll();
		}
		else {
			resultado = topicoRepository.findByCurso_Nombre(cursoNombre);
		}
		
		return TopicoDTO.convertir(resultado);
	}

	public Topico registrar(TopicoForm topicoForm) {
		Optional<Usuario> usuario= usuarioRepository.findById(topicoForm.getIdUsuario());
		Optional<Curso> curso = cursoRepository.findByNombre(topicoForm.getCursoNombre());
	
		Topico topico = topicoForm.convertir(usuario, curso);
		
		return topicoRepository.save(topico);
	}
	
	
	
}
