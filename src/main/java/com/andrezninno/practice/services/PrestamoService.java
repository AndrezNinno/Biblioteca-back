package com.andrezninno.practice.services;

import org.springframework.stereotype.Service;

import com.andrezninno.practice.domain.Prestamo;
import com.andrezninno.practice.repositories.PrestamoRepository;

@Service
public class PrestamoService {
	
	private final PrestamoRepository pr;

	public PrestamoService(PrestamoRepository pr) {
		super();
		this.pr = pr;
	}
	
	public Prestamo guardarActualizarPrestamo(Prestamo p) {
		return pr.save(p);
	}

}
