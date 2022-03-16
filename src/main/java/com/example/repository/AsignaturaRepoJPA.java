package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.repository.entity.Asignatura;

public interface AsignaturaRepoJPA extends JpaRepository<Asignatura, Integer>{
	
	@Query(value="select * from asignatura", nativeQuery=true)
	public List<Asignatura> listar();
}
