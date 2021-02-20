package com.andrezninno.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrezninno.practice.domain.Bibliotecario;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long>{

	boolean existBibliotecarioByTipoDocumentoAndDocumento(String tipoDocumento, String documento);
}
