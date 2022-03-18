package com.example.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.example.repository.AsignaturaRepoJPA;
import com.example.repository.entity.Asignatura;
import com.example.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class AsignaturaRestControllerTest {
	private Asignatura a1, a2;

	@Autowired
	AsignaturaRepoJPA repo;

	@Autowired
	AsignaturaService service;

	@Autowired
	AsignaturaRestController restcontroller;

	@Mock
	AsignaturaService serviceMock;

	@InjectMocks
	AsignaturaRestController restcontrollerMock;

	@BeforeEach
	void setUp() throws Exception {
		repo.deleteAll();

		a1 = new Asignatura();
		a1.setNombre("Dibujo Tecnico");
		a1.setDescripcion("enseñanza de geometria");
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
	void testListarAsignatura() {
		// GIVEN:

		assertEquals(2, service.listar().size(), "2 emp en BBDD");

		// WHEN:
		List<Asignatura> le = repo.listar();

		// THEN:
		assertEquals(2, ((List<Asignatura>) le).size(), "Hay 2 asignaturas en BBDD");
	}

	@Test
	void testDevuelveAsignatura() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "2 emp en BBDD");

		// WHEN:
		ResponseEntity<Asignatura> re = restcontroller.listarAsignaturaPorId(a1.getId());

		// THEN:
		assertEquals(a1, re.getBody(), "asignatura e1");
		assertThat(re.getStatusCodeValue()).isEqualTo(200);
		assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testModificarAsignatura() {
		// GIVEN:

		assertEquals(2, service.listar().size(), "2 emp en BBDD");

		// WHEN:
		String nuevoNombre = "Inglés";
		a2.setNombre(nuevoNombre);
		restcontroller.modificarAsignatura(a2);

		// THEN:
		assertEquals(2, service.listar().size(), "Sigue habiendo dos asignaturas en BBDD");
		assertEquals(nuevoNombre, service.getById(a2.getId()).getNombre(), "Modificado nombre");
	}

	@Test
	void testEliminarAsignatura() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		restcontroller.eliminarAsignaturaPorId(a1.getId());

		// THEN:
		assertEquals(1, service.listar().size(), "Solo queda 1 asignatura");
	}

	@Test
	void testInsertarAsignatura_v3_idIsNotNull() {
		// GIVEN:

		// WHEN:
		Asignatura e3 = new Asignatura();
		e3.setId(88);
		e3.setNombre("Nombre");
		e3.setDescripcion("descripcion");
		ResponseEntity<Asignatura> re = restcontroller.insertarAsignatura(e3);

		// THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 id is not null");
	}

	@Test
	void testInsertarAsignatura_v3_NombreIsNull() {
		// GIVEN:

		// WHEN:
		Asignatura e3 = new Asignatura();
		e3.setDescripcion("descripcion");
		ResponseEntity<Asignatura> re = restcontroller.insertarAsignatura(e3);

		// THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 nombre is null");
	}

	@Test
	void testInsertarAsignatura_v3() {
		// GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");

		// WHEN:
		Asignatura e3 = new Asignatura();
		e3.setNombre("Luisa");
		e3.setDescripcion("descripcion");
		ResponseEntity<Asignatura> re = restcontroller.insertarAsignatura(e3);

		// THEN:
		assertAll(() -> assertEquals(HttpStatus.CREATED, re.getStatusCode(), "Código 201 creado OK"),
				() -> assertEquals(3, service.listar().size(), "Ya hay tres asignaturas en BBDD"));
	}

	@Test
	void testInsertarAsignatura_v3_Exception() throws Exception {
		// GIVEN:
		when(serviceMock.inserta(a1)).thenThrow(new Exception());

		// WHEN:
		ResponseEntity<Asignatura> re = restcontrollerMock.insertarAsignatura(a1);

		// THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE /* INTERNAL_SERVER_ERROR */, re.getStatusCode(), "Excepción");

	}

}
