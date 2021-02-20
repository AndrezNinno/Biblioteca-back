package com.andrezninno.practice.services;

import java.util.ArrayList;
import java.util.List;

import com.andrezninno.practice.domain.Libro;
import com.andrezninno.practice.repositories.LibroRepository;

import org.springframework.stereotype.Service;

@Service
public class LibroService {
	
	private final LibroRepository libroRepository;
	
	//Constructor

	public LibroService(LibroRepository libroRepository) {
		super();
		this.libroRepository = libroRepository;
	}
	
	//Métodos

	public List<Libro> buscarLibros(){
		List<Libro> libros = new ArrayList<>();
		return libros;
	}
	
	public boolean existeLibro(String isbn) {
		return this.libroRepository.existsLibroByIsbn(isbn);
	}
	
	public Libro guardarActualizarLibro(Libro nuevoLibro) {
		return this.libroRepository.save(nuevoLibro);
	}

	public Libro buscarLibroPorIsbn(String isbn) {
		return libroRepository.findLibroByIsbn(isbn);
	}

	public void eliminarLibro(Libro l) {
		libroRepository.delete(l);
	}

	public List<Libro> listarLibros() {
		return libroRepository.findAll();
	}
}
