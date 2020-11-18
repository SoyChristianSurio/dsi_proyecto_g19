package com.sigsaaqyf.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sigsaaqyf.app.models.services.ICicloService;

@Controller
public class AdminController {

	@Autowired
	ICicloService cicloService;
	
	@GetMapping("/")
	public String homeG(Model model) {
		
		return "redirect:/usuario/registro";
	}
	
	@GetMapping("/admin/home")
	public String homeG(@ModelAttribute("username")String username, Model model) {
		model.addAttribute("usuario", username);
		model.addAttribute("ciclos", cicloService.findAllByActivo(false) );
		return "admin/home";
	}
	
	@PostMapping("/admin/home")
	public String homeP(@ModelAttribute("username")String username, Model model) {
		model.addAttribute("usuario", username);
		return "admin/home";
	}
	
	
	
	@GetMapping("/departamentos")
	public String departamentosLista(Model model){
		
		return "";
	}
}
