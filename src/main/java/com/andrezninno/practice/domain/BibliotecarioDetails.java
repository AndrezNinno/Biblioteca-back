package com.andrezninno.practice.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BibliotecarioDetails implements UserDetails{
	
	private String usuario;
	private String contrasenia;
	private String nombre;
	private String tipoDocumento;
	private String documento;
	private List<GrantedAuthority> authorities;

	public BibliotecarioDetails() {
		super();
	}
	
	public BibliotecarioDetails(String usuario) {
		super();
		this.usuario = usuario;
	}

	public BibliotecarioDetails(Bibliotecario b) {
		this.usuario = b.getUsuario();
		this.contrasenia = b.getContrasenia();
		this.nombre = b.getNombre();
		this.tipoDocumento = b.getTipoDocumento();
		this.documento = b.getDocumento();
		/*this.authorities = Arrays.stream(b.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
				*/
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasenia;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
