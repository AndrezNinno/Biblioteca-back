package com.andrezninno.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrezninno.practice.domain.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
