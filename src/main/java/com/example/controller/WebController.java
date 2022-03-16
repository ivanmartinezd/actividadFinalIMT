package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.AsignaturaService;
import com.example.service.UsuarioService;

@Controller
public class WebController {

	@Autowired
	AsignaturaService asignaturaService;
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/listar")
	public String listarAsig(Model model) {
		model.addAttribute("listarAsig", asignaturaService.listar());
		return "listAsignatura";
	}
	@GetMapping("/listarUsu")
	public String listarUsu(Model model) {
		model.addAttribute("listarUsu", usuarioService.listarPorRolAdmin2());
		return "listUsuarios";
	}
	

	@GetMapping("/error")
	public String errorPage() {
		return "error";
	}

}
