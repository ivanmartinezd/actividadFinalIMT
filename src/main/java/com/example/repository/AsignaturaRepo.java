package com.example.repository;

import java.util.List;

import com.example.repository.entity.Asignatura;

public interface AsignaturaRepo {
	List<Asignatura> listar();
	List<Asignatura> listarCuyoNombreContiene(String texto_nombre);
}
