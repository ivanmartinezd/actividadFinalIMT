package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AsignaturaRepoJPA;
import com.example.repository.entity.Asignatura;
import com.example.service.AsignaturaService;

@Service
public class AsignaturaServicempl implements AsignaturaService {
	
	@Autowired
	AsignaturaRepoJPA asignaturaRepo;

	@Override
	public List<Asignatura> listar() {
		return asignaturaRepo.listar();
	}

	@Override
	public Asignatura listarPorId(int id) {
		return asignaturaRepo.listarPorId(id);
	}

	@Override
	public void eliminar(int id) {
		asignaturaRepo.deleteById(id);
		
	}

	@Override
	public void eliminarTodos() {
		asignaturaRepo.deleteAll();
	}

	@Override
	public Asignatura modificar(Asignatura asignatura) {
		
		return asignaturaRepo.save(asignatura);
	}
	
	@Override
	public Asignatura inserta(Asignatura emp) throws Exception {
		return asignaturaRepo.save(emp);
	}

}
