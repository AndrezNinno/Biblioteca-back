package com.andrezninno.practice.responses;

import java.util.List;

import com.andrezninno.practice.domain.Libro;

public class listarLibrosResponse {

	private List<Libro> libros;

	public listarLibrosResponse(List<Libro> libros) {
		super();
		this.libros = libros;
	}

	public listarLibrosResponse() {
		super();
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}
