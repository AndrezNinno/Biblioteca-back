package com.andrezninno.practice.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.andrezninno.practice.domain.Ejemplar;
import com.andrezninno.practice.repositories.EjemplarRepository;

@Service
public class EjemplarService {
	
	private final EjemplarRepository er;

	public EjemplarService(EjemplarRepository er) {
		super();
		this.er = er;
	}

	public Ejemplar guardarEjemplar(Ejemplar e) {
		return er.save(e);
	}

	public Ejemplar buscarUltimoEjemplarAgregado() {
		return er.findLastEjemplar();
	}

	public void eliminarEjemplar(Ejemplar e) {
		er.delete(e);
	}

	public List<Ejemplar> buscarEjemplaresDisponible(boolean estado, Long id_libro) {
		return er.findByEstadoAlsoIdLibro(estado, id_libro);
	}
}
