package com.example.service;

import java.util.List;

import com.example.repository.entity.Usuario;

public interface UsuarioService {
	List<Usuario> listar();
	Usuario buscarPorUsername(String username);
	List<Usuario> listarPorRolAdmin();
	List<Usuario> listarPorRolAdmin2();

}
