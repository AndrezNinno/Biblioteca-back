package com.andrezninno.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.andrezninno.practice.requests.AutenticacionRequest;
import com.andrezninno.practice.requests.BibliotecarioRequest;
import com.andrezninno.practice.responses.AutenticacionResponse;
import com.andrezninno.practice.responses.RegistroResponse;
import com.andrezninno.practice.services.BibliotecarioService;
import com.andrezninno.practice.util.JwtUtil;

@RestController
public class BibliotecarioController {

	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	@Autowired
	private BibliotecarioService bs;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value="/autenticar", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AutenticacionRequest request) throws Exception {
		try {
			myAuthenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasenia()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Usuario o contraseña incorrecta");
		}
		
		final UserDetails bibliotecario = bs.loadUserByUsername(request.getUsuario());
		
		final String jwt = jwtTokenUtil.generateToken(bibliotecario);
		
		return ResponseEntity.ok(new AutenticacionResponse(jwt));
	}
	
	@RequestMapping(value="/bibliotecario/nuevo", method = RequestMethod.POST)
	public ResponseEntity<RegistroResponse> crearNuevoBibliotecario(@RequestBody BibliotecarioRequest nuevoBibliotecario) {
		return ResponseEntity.ok().body(this.bs.agregarUsuario(nuevoBibliotecario));
	}
}
