package com.andrezninno.practice.requests;

public class PeticionGuardarLibro {

	private String isbn;
	private String titulo;
	
	public PeticionGuardarLibro(String isbn, String titulo) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
	}

	public PeticionGuardarLibro() {
		super();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "PeticionGuardarLibro [isbn=" + isbn + ", titulo=" + titulo + "]";
	}
}
