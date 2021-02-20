package com.andrezninno.practice.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "libros")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLibro;
	private Integer cantidadTotal, cantidadDisponible;
	private String isbn, titulo;
	private boolean palindrome, mayor30;

	@JsonBackReference
	@OneToMany(mappedBy = "libro")
	private List<Ejemplar> ejemplares;

	public Libro(Long idLibro, Integer cantidadTotal, Integer cantidadDisponible, String isbn, String titulo,
			boolean palindrome, boolean mayor30, List<Ejemplar> ejemplares) {
		super();
		this.idLibro = idLibro;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
		this.isbn = isbn;
		this.titulo = titulo;
		this.palindrome = palindrome;
		this.mayor30 = mayor30;
		this.ejemplares = ejemplares;
	}

	public Libro() {
		super();
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
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

	public boolean isMayor30() {
		return mayor30;
	}

	public void setMayor30(boolean mayor30) {
		this.mayor30 = mayor30;
	}

	public boolean isPalindrome() {
		return palindrome;
	}

	public void setPalindrome(boolean palindrome) {
		this.palindrome = palindrome;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", cantidadTotal=" + cantidadTotal + ", cantidadDisponible="
				+ cantidadDisponible + ", isbn=" + isbn + ", titulo=" + titulo + ", palindrome=" + palindrome
				+ ", mayor30=" + mayor30 + "]";
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}
}
