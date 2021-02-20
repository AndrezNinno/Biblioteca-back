package com.andrezninno.practice.responses;

public class RegistroResponse {
	
	private String respuesta;
	private String error;
	private boolean exito;
	
	public RegistroResponse() {
		super();
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
