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

import com.sigsaaqyf.app.models.dao.IUsuarioDao;
import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.UsuarioEditarAdmin;
import com.sigsaaqyf.app.models.forms.UsuarioRegistro;
import com.sigsaaqyf.app.models.services.IUsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	IUsuarioDao usuarioDao; //objeto de acceso a tabla Usuarios de la BD
	@Autowired
	IUsuarioService usuarioService;
	
	//-----HANDLERS PARA EL REGISTRO DE USUARIOS----------------
	@GetMapping({"/","/registro"})
	public String registroG(Model model) {
		UsuarioRegistro usuario = new UsuarioRegistro();
		model.addAttribute("titulo", "Registro de Usuario");
		model.addAttribute("usuario", usuario);		
		return "usuario/registro";		
	}
	
	@PostMapping("/registro")
	public String registroP(@Valid @ModelAttribute("usuario")UsuarioRegistro myUsuario, BindingResult result, Model model) {		
		if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de Usuario");
            return "usuario/registro";
        }
		try {
			if(!myUsuario.getPassword().equals(myUsuario.getPassword2())) {
				throw new Exception("Las contraseñas no coinciden");
			}else if (!usuarioService.usernameDisponible(myUsuario.getCarnet())) {
				throw new Exception("Ya existe un usuario con ese carnet");
			}
			
		} catch (Exception e) {
			model.addAttribute("usuario", myUsuario);
            model.addAttribute("titulo", "Registro de Usuario");
            model.addAttribute("errorMessage", e.getMessage());
            return "usuario/registro";
		}
		
		usuarioService.registrarEstudiante(myUsuario);
		model.addAttribute("registrado", true);
		model.addAttribute("usuario", myUsuario);
		return "usuario/registro";
	}
	//-----FIN REGISTRO DE USUARIOS-------------
	
	//----- handlers de acceso a la gestion de usuario
	@GetMapping("/lista")
	public String listarTodos(Model model, RedirectAttributes flash) {
		model.addAttribute("titulo", "Lista de Usuarios");
		model.addAttribute("usuarios", usuarioDao.findAll());
		return "usuario/lista";
	}
	
	//------------------------------------------------------------------------################ EDITAR ##############
	@GetMapping("/editar/{id}")
	public String editarG(Model model, @PathVariable(name = "id")Long id) {
		UsuarioEditarAdmin usuario = usuarioService.usuarioEditarAdmin(id);	//recuperar usuario de la DB	
		model.addAttribute("usuario", usuario);                             //enviar usuario a la vista
		return "usuario/editar_adm"; 
	}
	
	@PostMapping("/editar")
	public String editarP(@Valid @ModelAttribute("usuario")UsuarioEditarAdmin myUsuario, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Usuario");
            
            return "usuario/editar_adm";
        }
		usuarioService.editarUsuarioAdmin(myUsuario);
		flash.addFlashAttribute("success", "Usuario "+myUsuario.getpNombre().concat(" ")
				.concat(myUsuario.getsNombre().concat(" "))
				.concat(myUsuario.getpApellido()).concat(" ")
				.concat(myUsuario.getsApellido()).concat(" fué Editado exitosamente"));;
		return "redirect:/usuario/lista"; 
	}
	//----------------------------------------------------------------------- ################# Activación/Desactivación ##########################
	@GetMapping("/cambiarEstado/{id}/{activo}")
	public String cambiarEstado(@PathVariable(name = "id")Long id, @PathVariable(name = "activo")Boolean activo, Model model,RedirectAttributes flash) {
		Usuario u = usuarioService.findById(id);
		u.setActivo(!activo);
		usuarioDao.save(u);
		if (activo) {
			flash.addFlashAttribute("warning", "Usuario "+u.getpNombre().concat(" ")
														.concat(u.getsNombre().concat(" "))
														.concat(u.getpApellido()).concat(" ")
														.concat(u.getsApellido()).concat(" fué dado de baja"));
		} else {
			flash.addFlashAttribute("info",   "Usuario "+u.getpNombre().concat(" ")
														.concat(u.getsNombre().concat(" "))
														.concat(u.getpApellido()).concat(" ")
														.concat(u.getsApellido()).concat(" fué Activado"));
		}
		System.out.println(activo+" "+id);
		return "redirect:/usuario/lista";
	}
	//--------------------------------------------------
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id")Long id, RedirectAttributes flash) {
		Usuario u = usuarioService.findById(id);
		
		flash.addFlashAttribute("warning",   "Usuario "+u.getpNombre().concat(" ")
				.concat(u.getsNombre().concat(" "))
				.concat(u.getpApellido()).concat(" ")
				.concat(u.getsApellido()).concat(" fué Eiminado")); 
		usuarioDao.delete(u);
		return "redirect:/usuario/lista";
	}
}












