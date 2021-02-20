package com.andrezninno.practice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bibliotecarios")
public class Bibliotecario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long idBibliotecario;
	private String usuario;
	private String contrasenia;
	private String nombre;
	private String tipoDocumento;
	private String documento;
	private String roles;
	
	public Bibliotecario(Long idBibliotecario, String usuario, String contrasenia, String nombre, String tipoDocumento,
			String documento) {
		super();
		this.idBibliotecario = idBibliotecario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}

	public Bibliotecario() {
		super();
	}

	public Long getIdBibliotecario() {
		return idBibliotecario;
	}

	public void setIdBibliotecario(Long idBibliotecario) {
		this.idBibliotecario = idBibliotecario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
}
