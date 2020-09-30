package com.sigsaaqyf.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sigsaaqyf.app.models.services.IMateriaService;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	IMateriaService materiaService;
	
	@GetMapping("/")
	public String lista(Model model) {
		model.addAttribute("materias",materiaService.findAll() );
		return "/materia/lista";
	}
}
