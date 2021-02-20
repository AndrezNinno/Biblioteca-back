package com.andrezninno.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andrezninno.practice.domain.Bibliotecario;
import com.andrezninno.practice.repositories.BibliotecarioRepository;
import com.andrezninno.practice.responses.RegistroResponse;

@Service
public class BibliotecarioService implements UserDetailsService{
	
	@Autowired
	BibliotecarioRepository bibliotecarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RegistroResponse agregarUsuario(Bibliotecario b) {
		RegistroResponse response = new RegistroResponse();
		
		if(!bibliotecarioRepository.existBibliotecarioByTipoDocumentoAndDocumento(b.getTipoDocumento(), b.getDocumento())) {
			
		} else {
			response.setRespuesta("El documento que está usando actualmente");
		}
		
		return response;
	}

}
