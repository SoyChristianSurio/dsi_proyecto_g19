package com.sigsaaqyf.app.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sigsaaqyf.app.models.dao.IMatriculaDao;
import com.sigsaaqyf.app.models.entity.Matricula;
import com.sigsaaqyf.app.models.entity.SolicitudRevision;
import com.sigsaaqyf.app.models.forms.UsuarioRegistro;
import com.sigsaaqyf.app.models.services.ICicloService;
import com.sigsaaqyf.app.models.services.IRevisionService;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

	@Autowired
	IMatriculaDao matriculaDao;
	@Autowired
	IRevisionService revisionService;
	
	@GetMapping("/")
	public String raiz(Model model,Principal prin) {
		List<Matricula> m = matriculaDao.findAllMatriculasActivasByUsername(prin.getName());	// Obtener las matriculas del usuario para el ciclo activo
		for(Matricula my:m) {
			System.out.println(my.getAsignatura().getMateria().getNombre());
		}
		model.addAttribute("matriculas", m);
		model.addAttribute("solicitudesRevision", revisionService.findAllActiveByUsername(prin.getName()));
		return "estudiante/gestionar";
	}
	
	@GetMapping("/{idmatric}/solicitar_revision/{ideva}")
	public String solicitarRevision(@PathVariable(name = "idmatric")Long idMatr, @PathVariable(name = "ideva")Long idEva, Model model) {
		Matricula m = matriculaDao.findById(idMatr).orElse(null);
		for(SolicitudRevision sr : revisionService.findAllActiveByUsername(m.getEstudiante().getUsername())) {
			if(sr.getEvaluacion().getId()==idEva) {
				return "redirect:/estudiante/ver_revision/"+sr.getId();
			}
		}
		revisionService.guardar(idMatr, idEva);
		return "redirect:/estudiante/";
	}
	
	@GetMapping("/ver_revision/{idrev}")
	public String verRevision(@PathVariable(name = "idrev")Long idRev, Model model,RedirectAttributes flash) {
		if(revisionService.findById(idRev).getEvaluacion().getAsignatura().getMateria().getDepartamento().getJefatura()==null) {
			flash.addFlashAttribute("error", "El departamento de la materia actualmente no posee jefatura, imposible visualizar solicitud");
			return "redirect:/estudiante/";
		}
		model.addAttribute("sol", revisionService.findById(idRev));
		return "estudiante/ver-revision";
	}
}
