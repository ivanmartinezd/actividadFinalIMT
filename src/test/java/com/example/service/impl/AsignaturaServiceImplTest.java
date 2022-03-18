package com.example.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.repository.AsignaturaRepoJPA;
import com.example.repository.entity.Asignatura;
import com.example.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class AsignaturaServiceImplTest {
	private Asignatura a1, a2;

	@Autowired
	AsignaturaRepoJPA repo;

	@Autowired
	AsignaturaService service;

	@BeforeEach
	void setUp() throws Exception {
		repo.deleteAll();

		a1 = new Asignatura();
		a1.setNombre("Literatura ambigua");
		a1.setDescripcion("enseñanza de libros griegos");
		a1 = repo.save(a1);

		a2 = new Asignatura();
		a2.setNombre("Musica");
		a2.setDescripcion("musica para la relajacion e interpretacion del cerebro humano mediante sonido");
		a2 = repo.save(a2);
	}

	@AfterEach
	void tearDown() throws Exception {
		repo.deleteAll();
	}

	@Test
	void testListar() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "2 asg en BBDD");

		// WHEN:
		List<Asignatura> le = service.listar();

		// THEN:
		assertEquals(2, le.size(), "Hay 2 asignatura en BBDD");
	}

	@Test
	void testListarFiltroNombre() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		List<Asignatura> le = service.listarFiltroNombre("u");

		// THEN:
		assertEquals(1, le.size(), "Las dos tienen una 'u' en el nombre");
	}

	@Test
	void testListarConJPA() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		List<Asignatura> le = service.listarFiltroNombre("%a%");

		// THEN:
		assertEquals(2, le.size(), "Las dos tienen 'a' en el nombre.");
	}

	@Test
	void testListarFiltroNombreEs() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		List<Asignatura> le1 = service.listarFiltroNombreEs("Ana");
		List<Asignatura> le2 = service.listarFiltroNombreEs("u");

		// THEN:
		assertAll(() -> assertEquals(1, le1.size(), "Hay 1 asignatura Ana"),
				() -> assertEquals(0, le2.size(), "Hay 0 asignatura que se llamen 'u'"));
	}

	@Test
	void testInserta() throws Exception {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		Asignatura e3 = new Asignatura();
		e3.setNombre("N3");
		e3.setDescripcion("AP3");
		e3 = service.inserta(e3);

		// THEN:
		assertEquals(3, service.listar().size(), "Hay 3 asignaturas");
	}

	@Test
	void testModificar() {
		// GIVEN:

		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		String nuevoNombre = "Marianico";
		a2.setNombre(nuevoNombre);
		service.modificar(a2);

		// THEN:
		assertEquals(2, service.listar().size(), "Sigue habiendo dos asignaturas en BBDD");
		assertEquals(nuevoNombre, service.getById(a2.getId()).getNombre(), "Mod nombre");
	}
	
	@Test
	void testEliminar() {
		//GIVEN:
			assertEquals(2, service.listar().size(), "Hay dos asignatura en BBDD");
			
		//WHEN:
			service.eliminar( a1.getId() );
			
		//THEN:
			assertEquals(1, service.listar().size(), "Solo queda 1 asignatura");
	}
	
	@Test
	void testGetById() {
		//GIVEN:
			assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");
			
		//WHEN:
			Asignatura e3 = service.getById( a1.getId() );
			
		//THEN:
			assertEquals (a1.getId(), e3.getId(), "Misma asignatura");
			assertNotNull(e3, "Empleado válido");
	}

}
