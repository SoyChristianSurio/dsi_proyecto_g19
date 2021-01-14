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

import com.sigsaaqyf.app.models.dao.IRoleDao;
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
	@Autowired
	IRoleDao roleDao;
	
//################################################  REGISTRO DEL USUARIO   #########################################
	@GetMapping("/registro")
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
				throw new Exception("Ya existe, escriba uno diferente");
			}
			
		} catch (Exception e) {
			model.addAttribute("usuario", myUsuario);
            model.addAttribute("titulo", "Registro de Usuario");
            model.addAttribute("errorMessage", e.getMessage());
            return "usuario/registro";
		}
		
		usuarioService.registrarEstudiante(myUsuario); 								// Servicio que persiste los datos del usuario
		model.addAttribute("registrado", true);
		model.addAttribute("usuario", myUsuario);
		return "usuario/registro";
	}
//################################################ ADMINISTRADOR REGISTRA A UN USUARIO   #########################################
	@GetMapping("/admin/registro")
	public String adminRegistroG(Model model) {
		UsuarioEditarAdmin usuario = usuarioService.usuarioEditarAdmin(null);	// recuperar usuario de la DB	
		model.addAttribute("usuario", usuario);                             	// enviar usuario a la vista
		model.addAttribute("roles", roleDao.findAll());							// enviar lista de roles para el select
		model.addAttribute("msjCrear", "Creación de nuevo Usuario");			// encabezado del panel principal
		return "usuario/editar_adm"; 
	}


	@PostMapping("/admin/registro")
	public String AdminRegistroP(	@Valid @ModelAttribute("usuario")UsuarioEditarAdmin myUsuario, 
									BindingResult result, Model model, RedirectAttributes flash) {		
		
		if (result.hasErrors()) {													// Si hay errores con las validaciones de la clase "UsuarioEditarAdmin"
			model.addAttribute("roles", roleDao.findAll());							// se envian nuevamente la lista de roles para el select
			model.addAttribute("msjCrear", "Creación de nuevo Usuario");			// se envia nuevamente el encabezado del panel principal
			return "usuario/editar_adm";											// y se carga la vista nuevamente (los mensajes de error se envian automaticamente)
        }																			//
																					//
		if (!usuarioService.usernameDisponible(myUsuario.getUsername())) {			// Se valida que el nombre de usuario ingresado no esté ocupado
			model.addAttribute("usuario", myUsuario);   							//
			model.addAttribute("roles", roleDao.findAll());							//
			model.addAttribute("msjCrear", "Creación de nuevo Usuario");			//
			model.addAttribute("errorMessage", "Ya existe, escriba uno diferente");	//
			return "usuario/editar_adm";											//
		}																			//
																					//
		usuarioService.crearUsuarioAdmin(myUsuario);								// Se envía el formulario válido al respectivo servicio donde se guardará el usuario
																					//
		flash.addFlashAttribute("success", "Usuario "								// Se envía un mensaje para indicar que el usuario se guardó
				+ myUsuario.getpNombre()											//
				.concat(" ")														//
				.concat(myUsuario.getsNombre()										//
				.concat(" "))														//
				.concat(myUsuario.getpApellido())									//
				.concat(" ")														//
				.concat(myUsuario.getsApellido())									//
				.concat(" fué Editado exitosamente")								//
				);																	//
		return "redirect:/usuario/lista";											// Se redirecciona a la Lista de usuarios
	}
	
//###########################################################   GESTIONAR USUARIOS   #################################
	@GetMapping("/lista")
	public String listarTodos(Model model, RedirectAttributes flash) {
		model.addAttribute("titulo", "Lista de Usuarios");
		model.addAttribute("usuarios", usuarioDao.findAll());
		return "usuario/lista";
	}
	
//###################################################################   EDITAR   #################################
	@GetMapping("/editar/{id}")
	public String editarG(Model model, @PathVariable(name = "id")Long id) {
		UsuarioEditarAdmin usuario = usuarioService.usuarioEditarAdmin(id);	//recuperar usuario de la DB	
		model.addAttribute("usuario", usuario);                             //enviar usuario a la vista
		model.addAttribute("roles", roleDao.findAll());
		return "usuario/editar_adm"; 
	}
	
	@PostMapping("/editar")
	public String editarP(@Valid @ModelAttribute("usuario")UsuarioEditarAdmin myUsuario, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Usuario");
            model.addAttribute("roles", roleDao.findAll());
            return "usuario/editar_adm";
        }
		if (!usuarioService.usernameDisponible(myUsuario)) {
			model.addAttribute("usuario", myUsuario);
			model.addAttribute("errorMessage", "Ya existe, escriba uno diferente");
			model.addAttribute("titulo", "Editar Usuario");
			model.addAttribute("roles", roleDao.findAll());
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












