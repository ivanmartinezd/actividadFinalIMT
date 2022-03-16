package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.repository.UsuarioRepoJPA;
import com.example.repository.entity.Asignatura;
import com.example.repository.entity.Rol;
import com.example.repository.entity.Usuario;
import com.example.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	UsuarioRepoJPA usuarioDAO;
	
	@Override
	public List<Usuario> listar() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return usuarioDAO.findById(username).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buscarPorUsername (username);
	}

	@Override
	public List<Usuario> listarPorRolAdmin() {
		return usuarioDAO.listarPorRol("admin");
	}

	@Override
	public List<Usuario> listarPorRolAdmin2() {
		Rol rol1 =new Rol(1,"admin");
		return usuarioDAO.findByRol(rol1);
	}
	
	

}
