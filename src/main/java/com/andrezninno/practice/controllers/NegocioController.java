package com.andrezninno.practice.controllers;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrezninno.practice.domain.Ejemplar;
import com.andrezninno.practice.domain.Libro;
import com.andrezninno.practice.domain.Prestamo;
import com.andrezninno.practice.requests.PeticionEliminarLibro;
import com.andrezninno.practice.requests.PeticionGuardarLibro;
import com.andrezninno.practice.requests.PeticionPrestarLibro;
import com.andrezninno.practice.responses.CrearLibroEjemplarResponse;
import com.andrezninno.practice.responses.eliminarRestarLibroResponse;
import com.andrezninno.practice.responses.listarLibrosResponse;
import com.andrezninno.practice.responses.prestarEjemplarLibroResponse;
import com.andrezninno.practice.services.EjemplarService;
import com.andrezninno.practice.services.LibroService;
import com.andrezninno.practice.services.PrestamoService;
import com.andrezninno.practice.util.LibrosUtil;

/**
 * @author Andres Niño
 *
 */
@RestController
public class NegocioController {

	private final LibroService ls;
	private final EjemplarService es;
	private final PrestamoService ps;

	public NegocioController(LibroService ls, EjemplarService es, PrestamoService ps) {
		super();
		this.ls = ls;
		this.es = es;
		this.ps = ps;
	}

	/**
	 * Método encargado de la creación o adición de ejemplares a un libro
	 * 
	 * @param informacion
	 * @return Retorna un mensaje de éxito al completar la transacción
	 * 
	 */
	@PostMapping("/libro/crear")
	public ResponseEntity<CrearLibroEjemplarResponse> agregarAñadirLibro(@RequestBody PeticionGuardarLibro informacion) {
		if (this.ls.existeLibro(informacion.getIsbn())) {
			Libro l = ls.buscarLibroPorIsbn(informacion.getIsbn());

			Ejemplar e = new Ejemplar();
			e.setEstado(false);
			e.setLibro(l);

			e = es.guardarEjemplar(e);

			l.setCantidadDisponible(l.getCantidadDisponible() + 1);
			l.setCantidadTotal(l.getCantidadTotal() + 1);

			ls.guardarActualizarLibro(l);

			return ResponseEntity.status(HttpStatus.CREATED).body(new CrearLibroEjemplarResponse("Se añadió un nuevo ejemplar del libro " + l.getTitulo()));
			//return "Arreglar respuestas (se agregó un ejemplar)";
		} else {
			Libro l = new Libro();
			l.setCantidadDisponible(1);
			l.setCantidadTotal(1);
			l.setIsbn(informacion.getIsbn());
			l.setTitulo(informacion.getTitulo());
			l.setPalindrome(LibrosUtil.esPalindrome(informacion.getIsbn()));
			l.setMayor30(LibrosUtil.mayor30(informacion.getIsbn()));

			l = this.ls.guardarActualizarLibro(l);

			Ejemplar e = new Ejemplar();
			e.setEstado(false);
			e.setLibro(l);

			e = es.guardarEjemplar(e);

			return ResponseEntity.status(HttpStatus.CREATED).body(new CrearLibroEjemplarResponse("Se añadió el libro " + l.getTitulo()));
			//return "Arreglar respuestas (Se creó el libro)";
		}
	}

	
	
	/**
	 * Método para eliminar un libro o eliminar un ejemplar del mismo si hay más de 1
	 * 
	 * @param info
	 * @return Responde con un mensaje de la acción realizada y con el status correspondiente
	 */
	@PostMapping("/libro/eliminar")
	public ResponseEntity<eliminarRestarLibroResponse> eliminarRestarLibro(@RequestBody PeticionEliminarLibro info) {
		if (ls.existeLibro(info.getIsbn())) {
			Libro l = ls.buscarLibroPorIsbn(info.getIsbn());

			if (l.getCantidadTotal() > 1) {
				/*
				 * Si hay más de un libro solamente eliminaremos el último ejemplar agregado y
				 * decrementaremos dependiendo del estado de ese libro la cantidad de ejemplares
				 * disponibles y la cantidad de ejemplares totales
				 */
				Ejemplar e = es.buscarUltimoEjemplarAgregado();

				if (!e.isEstado()) {
					l.setCantidadDisponible(l.getCantidadDisponible() - 1);
				}

				l.setCantidadTotal(l.getCantidadTotal() - 1);

				es.eliminarEjemplar(e);
				ls.guardarActualizarLibro(l);
				
				return ResponseEntity.status(HttpStatus.OK).body(new eliminarRestarLibroResponse("Se eliminó un ejemplar de " + l.getTitulo()));
			} else {
				/*
				 * En este caso donde solamente hay 1 ejemplar se eliminará tanto el ejemplar
				 * como el libro
				 */
				Ejemplar e = es.buscarUltimoEjemplarAgregado();

				es.eliminarEjemplar(e);
				ls.eliminarLibro(l);
				
				return ResponseEntity.status(HttpStatus.OK).body(new eliminarRestarLibroResponse("Se eliminó el libro " + l.getTitulo()));
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new eliminarRestarLibroResponse("No se encontró un libro con el isbn " + info.getIsbn()));
		}
	}

	/**
	 * Método para realizar el préstamo de un libro con las condiciones establecidas en el ejercicio
	 * 
	 * @param info
	 * @return Retorna un mensaje junto con el status correspondiente en caso exitoso o en la excepción.
	 */
	@PostMapping("/libro/prestar")
	public ResponseEntity<prestarEjemplarLibroResponse> prestarLibro(@RequestBody PeticionPrestarLibro info) {
		if(ls.existeLibro(info.getIsbn())) {
			/*
			 * Vamos a prestar el libro
			 */
			Libro l = ls.buscarLibroPorIsbn(info.getIsbn());
			
			if(l.getCantidadDisponible() >= 1) {
				/*
				 * Si se puede prestar un libro porque hay al menos 1 disponible
				 */
				if(l.isPalindrome()) {
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(new prestarEjemplarLibroResponse("El libro tiene un isbn palíndrome, no se puede realizar el préstamo"));
				}
				
				// Buscamos un ejemplar que se encuentre disponible y cambiamos su estado
				List<Ejemplar> el = es.buscarEjemplaresDisponible(false, l.getIdLibro());
				
				Ejemplar e = el.get(0);
				
				e.setEstado(true);
				
				// Generamos el nuevo prestamo y lo rellenamos con las condicionales del ejercicio
				Prestamo p = new Prestamo();
				p.setEjemplar(e);
				p.setFechaPrestamo(new java.sql.Date(System.currentTimeMillis()));
				if(l.isMayor30()) {
					p.setFechaEntregaMaxima(LibrosUtil.fechaEntrega(p.getFechaPrestamo()));
				}
				p.setNombrePrestador(info.getPrestador());
				
				// Guardamos la actualización del ejemplar y el nuevo préstamo realizado
				ps.guardarActualizarPrestamo(p);
				es.guardarEjemplar(e);
				
				l.setCantidadDisponible(l.getCantidadDisponible()-1);
				ls.guardarActualizarLibro(l);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new prestarEjemplarLibroResponse("Se prestó un ejemplar del libro correctamente."));
			} else {
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new prestarEjemplarLibroResponse("No se encuentra un ejemplar disponible para realizar el préstamo."));
			}
		}else {
			return ResponseEntity
					.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new prestarEjemplarLibroResponse("No se encontró un libro con ese isbn."));
		}
	}
	
	/**
	 * Método para listar todos los libros que se encuentran dentro de la biblioteca.
	 * 
	 * @return Lista de todos los libros.
	 */
	@GetMapping("/libro/listar")
	public ResponseEntity<listarLibrosResponse> listarLibros() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new listarLibrosResponse(ls.listarLibros()));
	}
}
