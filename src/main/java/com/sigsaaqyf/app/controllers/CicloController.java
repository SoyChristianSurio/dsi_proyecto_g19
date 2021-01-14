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

import com.sigsaaqyf.app.models.entity.Ciclo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.services.ICicloService;
import com.sigsaaqyf.app.models.services.IMateriaImpartidaService;
import com.sigsaaqyf.app.models.services.IMateriaService;

@Controller
@RequestMapping("/ciclo")
public class CicloController {

	@Autowired
	ICicloService cicloService;
	@Autowired
	IMateriaService materiaService;
	@Autowired
	IMateriaImpartidaService materiaImpService;
	
	
	@GetMapping("/activo")
	public String gestionarActivo(Model model) {
		if(!cicloService.findAllByActivo(true).isEmpty()) {
			model.addAttribute("cicloGes", cicloService.findAllByActivo(true).get(0) );	
			
		}
		model.addAttribute("ciclos", cicloService.findAllByActivo(false) );
		
		return "ciclo/gestionar";
	}
//############################################################### CREAR ###################################################################
	@GetMapping("/crear")
	public String crearG(Model model) {
		Ciclo ciclo = new Ciclo();
		ciclo.setActivo(false);
		
		model.addAttribute("titulo", "Crear Ciclo");
		model.addAttribute("ciclo", ciclo);
		return "ciclo/crear";
	}
	@PostMapping("/crear")
	public String crearP(@Valid @ModelAttribute(name = "ciclo")Ciclo ciclo, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			
			model.addAttribute("titulo", "Crear Ciclo");
            model.addAttribute("ciclo", ciclo);
            return "ciclo/crear";
        }
		if(!cicloService.save(ciclo)) {
			
			model.addAttribute("titulo", "Crear Ciclo");
			model.addAttribute("ciclo", ciclo);
			model.addAttribute("errorCicloYaExiste", "Error, el ciclo "+ciclo.getSemestre()+" "+ciclo.getAnio()+" ya existe");
            return "ciclo/crear";
		}
		flash.addFlashAttribute("success", "ciclo "+ciclo.getSemestre()+" "+ciclo.getAnio()+" creado exitosamente");
		
		return "redirect:/ciclo/activo";
	}
//############################################################### EDITAR #################################################################
	@GetMapping("/editar/{id}")
	public String editarG(@PathVariable(name = "id")Long cicloId, Model model) {
		model.addAttribute("editar", true);
		model.addAttribute("titulo", "Editar Ciclo");
		model.addAttribute("ciclo", cicloService.findById(cicloId));
		return "ciclo/crear";
	}
	@PostMapping("/editar")
	public String editarP(@Valid @ModelAttribute(name = "ciclo")Ciclo ciclo, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("editar", true);
			model.addAttribute("titulo", "Editar Ciclo");
            model.addAttribute("ciclo", ciclo);
            return "ciclo/crear";
        }
		
		if(!cicloService.update(ciclo)) {
			model.addAttribute("editar", true);
			model.addAttribute("titulo", "Editar Ciclo");
			model.addAttribute("ciclo", ciclo);
			model.addAttribute("errorCicloYaExiste", "Error, el ciclo "+ciclo.getSemestre()+" "+ciclo.getAnio()+" ya existe");
            return "ciclo/crear";
		}
		flash.addFlashAttribute("success", "ciclo "+ciclo.getSemestre()+" "+ciclo.getAnio()+" actualizado exitosamente");
		return "redirect:/ciclo/activo";
	}
//############################################################### GESTIONAR #############################################################
	@GetMapping("/gestionar/{id}")
	public String gestionarG(@PathVariable(name = "id")Long cicloId, Model model) {
		model.addAttribute("cicloGes", cicloService.findById(cicloId) );
		model.addAttribute("ciclos", cicloService.findAllByActivo(false) );
		
		return "ciclo/gestionar";
	}		
//########################################################### ACTIVAR/DESACTIVAR ##########################################################
	@GetMapping("/activar/{id}")
	public String activar(@PathVariable(name = "id")Long idCiclo, Model model, RedirectAttributes flash) {
		if(cicloService.activar(idCiclo)==false) {
			flash.addFlashAttribute("error", "ERROR, no se pudo activar Ciclo");
			return "redirect:/ciclo/gestionar/"+idCiclo;
		}
		flash.addFlashAttribute("success", "Ciclo activado");
		return "redirect:/ciclo/gestionar/"+idCiclo;
	}
	@GetMapping("/desactivar/{id}")
	public String desactivar(@PathVariable(name = "id")Long idCiclo, Model model, RedirectAttributes flash) {
		if(cicloService.desactivar(idCiclo)==false) {
			flash.addFlashAttribute("error", "ERROR, no se pudo desactivar Ciclo");
			return "redirect:/ciclo/gestionar/"+idCiclo;
		}
		flash.addFlashAttribute("warning", "Ciclo desactivado");
		return "redirect:/ciclo/gestionar/"+idCiclo;
	}
//########################################################### ELIMINAR ##########################################################
	@GetMapping("/eliminar/{id}")
	public String eliminarG(@PathVariable(name="id")Long cicloId,Model model) {
		cicloService.eliminar(cicloId);
	return "redirect:/ciclo/activo";	
	}
//################################################## LISTADO IMPARTIR MATERIA ##########################################################
	@GetMapping("/impartir/{id}")
	public String impartirG(@PathVariable(name = "id")Long cicloId, Model model) {
		
		model.addAttribute("materias", cicloService.materiasNoImpartidas(cicloId));
		model.addAttribute("cicloGes", cicloService.findById(cicloId));
		return "ciclo/impartir";
	}
	
	@GetMapping("/impartir/{idc}/{idm}")
	public String impartirListaG(@PathVariable(name = "idm")Long materiaId, @PathVariable(name = "idc")Long cicloId, Model model,RedirectAttributes flash) {
		
		if(materiaService.findById(materiaId).getDepartamento()==null) {
			model.addAttribute("materias", cicloService.materiasNoImpartidas(cicloId));
			model.addAttribute("cicloGes", cicloService.findById(cicloId));
			model.addAttribute("error", "Error, la materia seleccionada no posee departamento");
			return "ciclo/impartir";
		}
		else if(materiaService.findById(materiaId).getDepartamento().getJefatura()==null) {
			model.addAttribute("materias", cicloService.materiasNoImpartidas(cicloId));
			model.addAttribute("cicloGes", cicloService.findById(cicloId));
			model.addAttribute("error", "Error, el departamento al que pertenece la materia seleccionada no posee jefe");
			return "ciclo/impartir";
		}
		MateriaImpartida m = materiaImpService.crear(cicloId, materiaId);
		
		flash.addFlashAttribute("success", ""+m.getMateria().getNombre()+" impartida en el Ciclo "+m.getCiclo().getSemestre()+" "+m.getCiclo().getAnio());
		return "redirect:/ciclo/impartir/"+cicloId;
	}
	
	
}
