package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.repository.entity.Asignatura;
import com.example.repository.entity.Rol;
import com.example.repository.entity.Usuario;

public interface UsuarioRepoJPA extends JpaRepository<Usuario, String> {
	@Query(value="select * from usuario where rol.rol=?1", nativeQuery=true)
	public List<Usuario> listarPorRol(String rol);

	
	public List<Usuario> findByRol(Rol rol);
	
}

