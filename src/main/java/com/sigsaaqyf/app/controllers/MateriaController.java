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
	//-------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------- ############### AGREGAR #################
	@GetMapping("/agregar")
	public String agrearG(Model model) {
		model.addAttribute("editar", false);
		model.addAttribute("myh3", "Agregar nueva Materia");
		model.addAttribute("materia", new MateriaEditar());
		model.addAttribute("departamentos", departamentoService.findAll());
		return "/materia/editar";
	}
	
	@PostMapping("/agregar")
	public String agrearP(@Valid @ModelAttribute("materia")MateriaEditar me,BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("editar", false);
			model.addAttribute("myh3", "Agregar nueva Materia");
			model.addAttribute("departamentos", departamentoService.findAll());
			return "/materia/editar";
		}
		materiaService.save(me);
		flash.addFlashAttribute("success", "Materia agregada exitosamente");
		return "redirect:/materia/lista";
	}
	//------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------########### EDITAR ###########
	@GetMapping("/editar/{id}")
	public String editarG(@PathVariable(name = "id")Long id, Model model) {
		model.addAttribute("editar", true);
		model.addAttribute("myh3", "Editar Materia");
		model.addAttribute("materia", materiaService.findByIdEdit(id));
		model.addAttribute("departamentos", departamentoService.findAll());
		return "/materia/editar";
	}
	
	@PostMapping("/editar")
	public String editarP(@Valid @ModelAttribute("materia")MateriaEditar me,BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("myh3", "Editar Materia");
			model.addAttribute("departamentos", departamentoService.findAll());
			return "/materia/editar";
		}
		materiaService.save(me);
		flash.addFlashAttribute("info", "Materia editada exitosamente");
		return "redirect:/materia/lista";
	}
	//--------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------- ################## ELIMINAR ###############

	@GetMapping("/eliminar/{id}")
	public String eliminarG(@PathVariable("id")Long id,Model model, RedirectAttributes flash) {
		materiaService.deleteById(id);
		flash.addFlashAttribute("warning", "Materia eliminada permanentemente");
		return "redirect:/materia/lista";
	}
	
	
	
}
