package com.sigsaaqyf.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/lista")//------------------------------------------ ################## LISTAR DEPARTAMENTOS #################
	public String listar(Model model) {
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("nuevoDepartamento", new Departamento());
		return "/departamento/lista";
	}
	
	@PostMapping("/agregar")//-------------------------------------------- ####################### AGREGAR DEPARTAMENTO ###############
	public String agregar(@Valid @ModelAttribute("nuevoDepartamento")Departamento departamento,
				BindingResult result, Model model, RedirectAttributes flash) {
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
	
	@GetMapping("/editar/{id}")//------------------------------------------ ################## EDITAR DEPARTAMENTO #################
	public String editar(@PathVariable(name = "id")Long id, Model model) {
		Departamento departamento = departamentoService.findById(id);
		if(departamento==null) {
			model.addAttribute("error", "El departamento que busca no existe");
		}
		
		model.addAttribute("departamento", departamento);
		model.addAttribute("departamentoNombre", departamento.getNombre() );
		if(departamento.getJefatura()!=null) model.addAttribute("departamentoJefatura", departamento.getJefatura().getJefe().getUsername() );
		model.addAttribute("departamentoMaterias", departamentoService.findById(id).getMaterias() );
		
		return "/departamento/editar";
	}
	
	@PostMapping("/editar")//------------------------------------------ ################## EDITAR DEPARTAMENTO #################
	public String editar(@Valid @ModelAttribute("departamento")Departamento departamento, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			Departamento departament = departamentoService.findById(departamento.getId());
			model.addAttribute("departamentoNombre", departament.getNombre() );
			if(departament.getJefatura()!=null) model.addAttribute("departamentoJefatura", departament.getJefatura().getJefe().getUsername() );
			model.addAttribute("departamentoMaterias", departament.getMaterias() );
			System.out.println(departamento.getId());
			System.out.println(departamento.getNombre());
			System.out.println(departamento.getJefatura());
			System.out.println(departamento.getMaterias().toString());
			return "/departamento/editar";
		}
		departamentoService.save(departamento);
		flash.addFlashAttribute("success",	"Se ha editado el nombre del departamento");
		
		return "redirect:/departamento/lista";
	}
}
