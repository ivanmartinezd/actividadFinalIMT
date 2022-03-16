package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repository.entity.Rol;

public interface RolRepoJPA extends JpaRepository<Rol, Integer> {

}
