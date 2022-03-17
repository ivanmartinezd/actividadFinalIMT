package com.example.service;

import java.util.List;

import com.example.repository.entity.Asignatura;



public interface AsignaturaService {
	List<Asignatura> listar();
	Asignatura listarPorId(int id);
	void eliminar(int id);
	void eliminarTodos();
	Asignatura modificar(Asignatura asignatura);
	Asignatura inserta(Asignatura emp) throws Exception;
}
