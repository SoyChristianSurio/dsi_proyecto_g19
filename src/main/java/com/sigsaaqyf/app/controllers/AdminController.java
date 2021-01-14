package com.sigsaaqyf.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sigsaaqyf.app.models.forms.UsuarioRegistro;
import com.sigsaaqyf.app.models.services.ICicloService;

@Controller
public class AdminController {

	@Autowired
	ICicloService cicloService;
	
	@GetMapping("/")
	public String raiz(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // Se obtiene la sesion actual
		//System.out.println();
		switch (auth.getAuthorities().toArray()[0].toString()) {
		case "ROLE_admin":
			
			System.out.println("entrando en case1: "+auth.getAuthorities().toArray()[0].toString());
			return "redirect:/ciclo/activo";
			
		case "ROLE_estudiante":
			System.out.println("entrando en case2: "+auth.getAuthorities().toArray()[0].toString());
			return "redirect:/estudiante/";
		
		}
		return "redirect:/usuario/registro";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(name = "error",required = false)String error, Model model, Principal principal, RedirectAttributes flash) {
		if(principal != null) {
			flash.addFlashAttribute("success", "sesión iniciada");
			return "redirect:/ciclo/activo";
		}
		
		if(error!=null) model.addAttribute("error", "nombre de usuario o contraseña incorrectos");	

		model.addAttribute("usuario", new UsuarioRegistro());
		return "usuario/login";
	}
	
}
