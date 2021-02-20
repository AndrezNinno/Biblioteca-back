package com.andrezninno.practice.requests;

public class PeticionPrestarLibro {

	private String isbn;
	private String prestador;

	public PeticionPrestarLibro(String isbn, String prestador) {
		super();
		this.isbn = isbn;
		this.prestador = prestador;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPrestador() {
		return prestador;
	}

	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}

	@Override
	public String toString() {
		return "PeticionPrestarLibro [isbn=" + isbn + ", prestador=" + prestador + "]";
	}

}
