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

import com.sigsaaqyf.app.models.entity.Materia;
import com.sigsaaqyf.app.models.forms.MateriaEditar;
import com.sigsaaqyf.app.models.services.IDepartamentoService;
import com.sigsaaqyf.app.models.services.IMateriaService;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	IMateriaService materiaService;
	
	@Autowired
	IDepartamentoService  departamentoService;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("materias",materiaService.findAll() );
		return "/materia/lista";
	}
	
	@GetMapping("/agregar")
	public String agrearG(Model model) {
		model.addAttribute("materia", new Materia());
		model.addAttribute("departamentos", departamentoService.findAll());
		return "/materia/editar";
	}
	
	
	//------------------------------------------------------------------------########### EDITAR ###########
	@GetMapping("/editar/{id}")
	public String editarG(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("materia", materiaService.findByIdEdit(id));
		model.addAttribute("departamentos", departamentoService.findAll());
		return "/materia/editar";
	}
	
	@PostMapping("/editar")
	public String editarP(@Valid @ModelAttribute("materia")MateriaEditar me,BindingResult result, Model model) {
		System.out.println(me);
		if(result.hasErrors()) {
			
			model.addAttribute("departamentos", departamentoService.findAll());
			return "/materia/editar";
		}
		materiaService.save(me);
		return "redirect:/materia/lista";
	}
	//--------------------------------------------------------------------------------------------------------
	
}
