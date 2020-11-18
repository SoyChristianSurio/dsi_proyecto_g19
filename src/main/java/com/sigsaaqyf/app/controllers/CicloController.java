package com.sigsaaqyf.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sigsaaqyf.app.models.entity.Ciclo;
import com.sigsaaqyf.app.models.services.ICicloService;

@Controller
@RequestMapping("/ciclo")
public class CicloController {

	@Autowired
	ICicloService cicloService;
	
	@GetMapping("/activo")
	public String gestionarActivo(Model model) {
		
		model.addAttribute("activo", cicloService.findAllByActivo(true) );
		model.addAttribute("ciclos", cicloService.findAllByActivo(false) );
		
		return "ciclo/gestionar";
	}
	
	@GetMapping("/gestionar/{id}")
	public String gestionarG(@PathVariable(name = "id")Long cicloId, Model model) {
		
		model.addAttribute("ciclos", cicloService.findAllByActivo(false) );
		
		return "ciclo/gestionar";
	}
}
