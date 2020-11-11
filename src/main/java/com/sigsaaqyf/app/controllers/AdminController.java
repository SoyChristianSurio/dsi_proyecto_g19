package com.sigsaaqyf.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@GetMapping("/")
	public String homeG(Model model) {
		
		return "redirect:/usuario/registro";
	}
	
	@GetMapping("/admin/home")
	public String homeG(@ModelAttribute("username")String username, Model model) {
		model.addAttribute("usuario", username);
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
