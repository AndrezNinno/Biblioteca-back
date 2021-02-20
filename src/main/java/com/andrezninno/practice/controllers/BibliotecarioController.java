package com.andrezninno.practice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andrezninno.practice.requests.AutenticacionRequest;
import com.andrezninno.practice.requests.BibliotecarioRequest;
import com.andrezninno.practice.responses.AutenticacionResponse;
import com.andrezninno.practice.responses.RegistroResponse;
import com.andrezninno.practice.services.BibliotecarioService;
import com.andrezninno.practice.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class BibliotecarioController {

	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	@Autowired
	private BibliotecarioService bs;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/bibliotecario/autenticar")
	public ResponseEntity<AutenticacionResponse> createAuthenticationToken(@RequestBody AutenticacionRequest request) throws Exception {
		try {
			myAuthenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasenia()));
		} catch (BadCredentialsException e) {
			throw new Exception("Usuario o contraseña incorrecta", e);
		}
		
		final UserDetails bibliotecario = bs.loadUserByUsername(request.getUsuario());
		
		final String jwt = jwtTokenUtil.generateToken(bibliotecario);
		
		return ResponseEntity.ok(new AutenticacionResponse(jwt));
	}
	
	@PostMapping("/bibliotecario/nuevo")
	public ResponseEntity<RegistroResponse> crearNuevoBibliotecario(@RequestBody BibliotecarioRequest nuevoBibliotecario) {
		return ResponseEntity.ok().body(this.bs.agregarUsuario(nuevoBibliotecario));
	}
}
