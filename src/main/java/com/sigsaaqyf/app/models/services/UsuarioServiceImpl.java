package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigsaaqyf.app.models.dao.IRoleDao;
import com.sigsaaqyf.app.models.dao.IUsuarioDao;
import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.UsuarioEditarAdmin;
import com.sigsaaqyf.app.models.forms.UsuarioRegistro;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired 
	IUsuarioDao usuarioDao;
	@Autowired
	IRoleDao roleDao;
	@Autowired
	IDepartamentoService departamentoService;
	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	public String primeraMayuscula(String s) {									// Devuelve el string con la primera mayúscula y el resto minúsculas
		if(s.isBlank()) return s;												//
		return s.substring(0, 1).toUpperCase()+s.substring(1).toLowerCase();	//
	}																			//
//--------------------------------------------------------------------------------	
	
	
	public boolean usernameDisponible(String username) {			// Recibe un String
		return !usuarioDao.findByUsername(username).isPresent();	// Retorna TRUE si el nombre de usuario está disponible y flase si ya está acupado
	}																//
//--------------------------------------------------------------------	
	
	
	public boolean usernameDisponible(UsuarioEditarAdmin user) {				// Recibe un objeto de tipo "UsuarioEditarAdmin"
		Optional<Usuario> u = usuarioDao.findByUsername(user.getUsername());	// Se usa al modificar el nombre de un usuario; busca en la base de datos si   
		if (u.isPresent()) {													// el string recibido ya está ocupado por otro usuario con id diferente del usuario
			if(u.get().getId()==user.getId())return true;						// en gestión
			else return false;													//
		}else return true;														//
	}																			//
//--------------------------------------------------------------------------------
	
	
	@Transactional
	public void registrarEstudiante(UsuarioRegistro u) {				// Recibe un objeto de tipo "UsuarioRegistro"
																		// 
		Usuario estudiante = new Usuario();								// Se usa para realizar el auto registro de un usuario
																		//
		estudiante.setUsername(u.getCarnet().toUpperCase());			// Guardar el carnet con letras en mayúscula
																		//
		estudiante.setpNombre(primeraMayuscula(u.getpNombre()));		// Guardar los nombres y apellidos con la inicial mayuscula
		estudiante.setsNombre(primeraMayuscula(u.getsNombre()));		//
		estudiante.setpApellido(primeraMayuscula(u.getpApellido()));	//
		estudiante.setsApellido(primeraMayuscula(u.getsApellido()));	//
																		//
		estudiante.setEmail(u.getCarnet().concat("@ues.edu.sv"));		// Establecer el correo electronico conformato CARNET@ues.edu.sv
		estudiante.setPassword(encoder.encode(u.getPassword()));		// Cifrar la contraseña establecida
		estudiante.setActivo(true);										// El usuario se registra como activo 
		estudiante.setEstudiante(true);									// El usuario es de tipo "estudiante"
		estudiante.setRole(roleDao.findByNombre("ROLE_estudiante"));
		usuarioDao.save(estudiante);									// 
		
	}																	//
//------------------------------------------------------------------------
	
	@Transactional
	public Usuario registrarEstudiante(Usuario u) {						// Recibe un objeto de tipo "Usuario"
																		// 
		Usuario estudiante = new Usuario();								// Se usa para realizar el auto registro de un usuario
																		//
		estudiante.setUsername(u.getUsername().toUpperCase());			// Guardar el carnet con letras en mayúscula
																		//
		estudiante.setpNombre(primeraMayuscula(u.getpNombre()));		// Guardar los nombres y apellidos con la inicial mayuscula
		estudiante.setsNombre(primeraMayuscula(u.getsNombre()));		//
		estudiante.setpApellido(primeraMayuscula(u.getpApellido()));	//
		estudiante.setsApellido(primeraMayuscula(u.getsApellido()));	//
																		//
		estudiante.setEmail(u.getUsername().concat("@ues.edu.sv"));		// Establecer el correo electronico conformato CARNET@ues.edu.sv
		estudiante.setPassword(encoder.encode(u.getPassword()));		// Cifrar la contraseña establecida
		estudiante.setActivo(true);										// El usuario se registra como activo 
		estudiante.setEstudiante(true);									// El usuario es de tipo "estudiante"
		estudiante.setRole(roleDao.findByNombre("ROLE_estudiante"));
		return usuarioDao.save(estudiante);									// 
		
	}																	//
//------------------------------------------------------------------------
	
	@Override
	@Transactional
	public void editarUsuarioAdmin(UsuarioEditarAdmin uea) { 		// Método que guarda los cambios realizados en un Usuario
		Usuario u;													//
		if(uea.getId()==null) {										//
			u=new Usuario();										//
			u.setActivo(true);										//
		}															//
		else u = this.findById(uea.getId());						//
																	//
		u.setpNombre(primeraMayuscula(uea.getpNombre()));			//
		u.setsNombre(primeraMayuscula(uea.getsNombre()));			//
		u.setpApellido(primeraMayuscula(uea.getpApellido()));		//
		u.setsApellido(primeraMayuscula(uea.getsApellido()));		//
		u.setRole(roleDao.findById(uea.getRole()).orElse(null));	//
		u.setEstudiante(uea.getEstudiante());						//
																	//
		if (uea.getEstudiante()) {									//
			u.setUsername(uea.getCarnet().toUpperCase());			//
		} else {													//
			u.setUsername(uea.getUsername());						//
		}															//
		u.setEmail(uea.getEmail());									//
		usuarioDao.save(u);											//
	}																//
//--------------------------------------------------------------------
	
	
	@Override
	@Transactional
	public Usuario crearUsuarioAdmin(UsuarioEditarAdmin uea) { 			// Se recibe un objeto tipo "UsuarioEditarAdmin" con la información validada para guardar
																		// un objeto de tipo "Usuario" en la base de datos.
		Usuario u = new Usuario();										
																		 
		u.setpNombre(primeraMayuscula(uea.getpNombre()));				// Se pasan los datos del objeto "UsuarioEditarAdmin" a uno del tipo "Usuario
		u.setsNombre(primeraMayuscula(uea.getsNombre()));				//
		u.setpApellido(primeraMayuscula(uea.getpApellido()));			//
		u.setsApellido(primeraMayuscula(uea.getsApellido()));			//
		u.setRole(roleDao.findById(uea.getRole()).orElse(null));		//
		u.setEstudiante(uea.getEstudiante());							//
		u.setEmail(uea.getEmail());										// *
		u.setActivo(true);												//
																		//
		if (u.getEstudiante()) {										// Si es de tipo estudiante se escribe el "carnet" como "username" escribiéndolo
			u.setUsername(uea.getCarnet().toUpperCase());				// en mayúsculas
		} else {														
			u.setUsername(uea.getUsername());							// Si no es estudiante, se guarda el nombre de usuario digitado en el formulario
		}																
		u.setPassword(encoder.encode(u.getUsername()));					// Se establece como contraseña el username ingresado									
		return usuarioDao.save(u);										// Se guarda el objeto en la base de datos.
	}																	//
//------------------------------------------------------------------------
	
	@Override
	public List<Usuario> findAll() {					//
		return (List<Usuario>) usuarioDao.findAll();	//
	}													//
//--------------------------------------------------------
	
	
	@Override
	public Usuario findById(Long id) {						//
		Usuario u = usuarioDao.findById(id).orElse(null);	//
		return u;											//
	}														//
//------------------------------------------------------------
	
	
	@Override 
	public UsuarioEditarAdmin usuarioEditarAdmin(Long id) { 	// dado el id retorna un usuario de tipo UsuarioEditarAdmin con los campos necesarios para editar al tipo Usuario
																//
		if (id==null) return new UsuarioEditarAdmin();			//
																//
		Usuario u = this.findById(id);							//
		if(u==null) return null;								//
		UsuarioEditarAdmin uea = new UsuarioEditarAdmin();		//
																//
		uea.setId(u.getId());									//
		uea.setpNombre(u.getpNombre());							//
		uea.setsNombre(u.getsNombre());							//
		uea.setpApellido(u.getpApellido());						//
		uea.setsApellido(u.getsApellido());						//
		uea.setEmail(u.getEmail());								//
		uea.setEstudiante(u.getEstudiante());					//
		if(u.getRole()!=null) uea.setRole(u.getRole().getId());	//
																//
		if(uea.getEstudiante()) {								//
			uea.setCarnet(u.getUsername());						//
			uea.setUsername(u.getUsername());					//
		}else {													//
			uea.setCarnet("XX99999");							//
			uea.setUsername(u.getUsername());					//
		}														//
		return uea;												//
	}															//
//----------------------------------------------------------------
	
	
	@Override
	public List<Usuario> findAllDocentes() {			//
		return usuarioDao.findAllByEstudiante(false);	//
	}													//
//--------------------------------------------------------
	
	
	@Override
	public String getNombreCompleto(Long id) {	//
		return "no implementado";				//
	}											//
//------------------------------------------------
	
	
	@Override
	public List<Usuario> findAllDocentesActivos() {					//
		return usuarioDao.findAllByEstudianteFalseAndActivoTrue();	//
	}																//
//--------------------------------------------------------------------
	
	
	@Override
	public List<Usuario> findAllDocentesSinJefatura() {					//
		List<Usuario> docentes = this.findAllDocentesActivos();			//
		List<Usuario> jefes = departamentoService.findAllJefes();		//
		List<Usuario> docentesSinJefatura = new ArrayList<Usuario>();	//
																		//
		for(Usuario u: docentes) {										//
			if(!jefes.contains(u)) {									//
				docentesSinJefatura.add(u);								//
			}															//
		}																//
		return docentesSinJefatura;										//
	}																	//
//------------------------------------------------------------------------


	@Override
	public Usuario findByUsername(String username) {
		return (Usuario)usuarioDao.findByUsername(username).orElse(null);
	}
	
	
	
}
