package com.andrezninno.practice.requests;

public class BibliotecarioRequest {

	private String usuario;
	private String contrasenia;
	private String nombre;
	private String tipoDocumento;
	private String documento;

	public BibliotecarioRequest(String usuario, String contrasenia, String nombre, String tipoDocumento,
			String documento) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}

	public BibliotecarioRequest() {
		super();
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
}
