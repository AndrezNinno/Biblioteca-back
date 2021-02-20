package com.andrezninno.practice.responses;

public class AutenticacionResponse {

	private final String jwt;

	public AutenticacionResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
