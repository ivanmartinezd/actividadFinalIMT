package com.example.restcontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.repository.entity.Asignatura;
import com.example.service.AsignaturaService;

@RestController
@RequestMapping("/api/empleados")
public class AsignaturaRestController {

	@Autowired
	AsignaturaService asignaturaService;

	@GetMapping
	@Cacheable(value = "asignaturas")
	public ResponseEntity<List<Asignatura>> listarAsignaturas() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		List<Asignatura> asig = asignaturaService.listar();
		if (asig == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(asig, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@CacheEvict(value = "asignaturas", allEntries = true)
	public ResponseEntity<Asignatura> listarAsignaturaPorId(@PathVariable("id") Integer id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Asignatura asig = asignaturaService.listarPorId(id);

		if (asig == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(asig, HttpStatus.OK);
	}

	@CacheEvict(value = "asignaturas", allEntries = true)
	@DeleteMapping
	public void eliminarAsignaturaTodos() {
		asignaturaService.eliminarTodos();
	}

	@CacheEvict(value = "asignaturas", allEntries = true)
	@DeleteMapping(value = "/{id}")
	public void eliminarAsignaturaPorId(@PathVariable("id") int id) {
		asignaturaService.eliminar(id);
	}

	@PutMapping
	@CacheEvict(value = "asignaturas", allEntries = true)
	public ResponseEntity<Asignatura> modificarAsignatura(@RequestBody Asignatura asignatura) {
		try {
			HttpHeaders headers = new HttpHeaders();
			if (asignatura.getNombre() == null || asignatura.getNombre().equals("")
					|| asignatura.getDescripcion() == null || asignatura.getDescripcion().equals("")) {
				headers.set("Message", "Ni el NOMBRE ni la DESCRIPCIÓN pueden ser nulos");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
			}
			Asignatura asig = asignaturaService.modificar(asignatura);
			URI newPath = new URI("/api/empleados/" + asig.getId());
			headers.setLocation(newPath);
			headers.set("Message", "Asignatura modificada correctamente con id: " + asig.getId());
			return new ResponseEntity<>(asig, headers, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@CacheEvict(value = "asignaturas", allEntries = true)
	public ResponseEntity<Asignatura> insertarAsignatura(@RequestBody Asignatura asignatura) {
		
		try {
			HttpHeaders headers = new HttpHeaders();
			if (asignatura.getId() != null) {
				headers.set("Message", "Para dar de alta una nueva asignatura, el ID debe llegar vacío");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
			} else if (asignatura.getNombre() == null || asignatura.getNombre().equals("")
					|| asignatura.getDescripcion() == null || asignatura.getDescripcion().equals("")) {
				headers.set("Message", "Ni el NOMBRE ni la DESCRIPCIÓN pueden ser nulos");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
			}

			Asignatura asig = asignaturaService.inserta(asignatura);
			URI newPath = new URI("/api/empleados/" + asig.getId());
			headers.setLocation(newPath);
			headers.set("Message", "Asignatura insertada correctamente con id: " + asig.getId());

			return new ResponseEntity<>(asig, headers, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
