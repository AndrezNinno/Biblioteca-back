package com.andrezninno.practice.requests;

public class PeticionEliminarLibro {

	private String isbn;

	public PeticionEliminarLibro(String isbn) {
		super();
		this.isbn = isbn;
	}

	public PeticionEliminarLibro() {
		super();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "PeticionEliminarLibro [isbn=" + isbn + "]";
	}
}
