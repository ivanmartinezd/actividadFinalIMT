package com.example.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;



@Entity
@Table
public class Rol implements GrantedAuthority {

	@Id
	@Column
	private Integer id;
	
	@Column
	private String rol;
	
	
	public Rol(Integer id, String rol) {
		super();
		this.id = id;
		this.rol = rol;
	}
	
	public Rol() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String getAuthority() {  // 'ADMIN' -> 'ROLE_ADMIN'  ## 'GESTOR'-> 'ROLE_GESTOR'
		return ("ROLE_"+this.rol).toUpperCase();
	}

	@Override
	public String toString() {
		return ("ROLE_"+this.rol).toUpperCase();
	}	
	
	
}
