package com.sigsaaqyf.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sigsaaqyf.app.models.entity.Departamento;
import com.sigsaaqyf.app.models.services.IDepartamentoService;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
	
	@Autowired
	IDepartamentoService departamentoService;
	
	@GetMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("nuevoDepartamento", new Departamento());
		return "/departamento/lista";
	}
	
	@PostMapping("/agregar")
	public String agregar(@Valid @ModelAttribute("nuevoDepartamento")Departamento departamento, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("error", "Departamento no guardado");
			model.addAttribute("departamentos", departamentoService.findAll());
			return "/departamento/lista";
		}
		departamentoService.save(departamento);
		flash.addFlashAttribute("success", "Departamento guardado");
		System.out.println(departamento.getNombre());
		return "redirect:/departamento/lista";
	}
}
