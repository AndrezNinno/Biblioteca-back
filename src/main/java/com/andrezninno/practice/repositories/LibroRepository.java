package com.andrezninno.practice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andrezninno.practice.domain.Libro;

public interface   LibroRepository extends JpaRepository<Libro, Long>{

	@Query("from Libro l order by l.titulo")
	List<Libro> buscarTodos();
	
	boolean existsLibroByIsbn(String isbn);

	Libro findLibroByIsbn(String isbn);
}
