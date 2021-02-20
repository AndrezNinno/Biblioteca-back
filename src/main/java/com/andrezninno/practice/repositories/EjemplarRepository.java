package com.andrezninno.practice.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andrezninno.practice.domain.Ejemplar;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{

	@Query(value = "select * from ejemplares order by id_ejemplar desc limit 1", nativeQuery = true)
	Ejemplar findLastEjemplar();

	@Query("from Ejemplar e where e.estado = ?1 and e.libro.idLibro = ?2")
	List<Ejemplar> findByEstadoAlsoIdLibro(boolean estado, Long id_libro);

}
