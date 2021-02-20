package com.andrezninno.practice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andrezninno.practice.domain.Bibliotecario;
import com.andrezninno.practice.domain.BibliotecarioDetails;
import com.andrezninno.practice.repositories.BibliotecarioRepository;
import com.andrezninno.practice.responses.RegistroResponse;

@Service
public class BibliotecarioService implements UserDetailsService{
	
	@Autowired
	BibliotecarioRepository bibliotecarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Bibliotecario> bibliotecario = bibliotecarioRepository.findBibliotecarioByUsuario(username);
		
		bibliotecario.orElseThrow(() -> new UsernameNotFoundException("No se encontró: " + username));
		
		return bibliotecario.map(BibliotecarioDetails::new).get();
	}
	
	public RegistroResponse agregarUsuario(Bibliotecario b) {
		RegistroResponse response = new RegistroResponse();
		
		if(!bibliotecarioRepository.existBibliotecarioByTipoDocumentoAndDocumento(b.getTipoDocumento(), b.getDocumento())) {
			if(!bibliotecarioRepository.existBibliotecarioByUsuario(b.getUsuario())) {
				if(bibliotecarioRepository.save(b) != null) {
					response.setRespuesta("Se registró exitosamente su usuario");
					response.setExito(true);
				} else {
					response.setError("Hubo un problema inesperado registrando su usuario, por favor contacte al servicio técnico");
					response.setExito(false);
				}
			} else {
				response.setError("El usuario " + b.getUsuario() + " ya se encuentra en uso");
				response.setExito(false);
			}
		} else {
			response.setError("El documento que está usando actualmente ya se encuentra registrado!");
			response.setExito(false);
		}
		
		return response;
	}

}
