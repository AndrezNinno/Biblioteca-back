package com.andrezninno.practice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrezninno.practice.domain.Bibliotecario;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long>{

	boolean existsBibliotecarioByTipoDocumentoAndDocumento(String tipoDocumento, String documento);

	Optional<Bibliotecario> findBibliotecarioByUsuario(String usuario);
	
	boolean existsBibliotecarioByUsuario(String usuario);
}
