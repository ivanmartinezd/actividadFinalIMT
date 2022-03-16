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

}
