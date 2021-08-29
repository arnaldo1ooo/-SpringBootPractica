package com.practica.springBootPractica.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.practica.springBootPractica.controller.dto.TopicoDTO;
import com.practica.springBootPractica.model.Topico;
import com.practica.springBootPractica.repository.TopicoRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;

	public List<TopicoDTO> listado(String cursoNombre) {
		List<Topico> resultado;
		
		if (cursoNombre == null) {
			resultado = topicoRepository.findAll();
		}
		else {
			resultado = topicoRepository.findByCurso_Nombre(cursoNombre);
			//resultado = topicoRepository.buscaTopicosPorCursoNombre(cursoNombre);
			//resultado = topicoRepository.buscaNativaTopicosPorCursoNombre(cursoNombre);
		}
		
		return TopicoDTO.convertir(resultado);
	}
	
	
	
}
