package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigsaaqyf.app.models.dao.IUsuarioDao;
import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.UsuarioEditarAdmin;
import com.sigsaaqyf.app.models.forms.UsuarioRegistro;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired 
	IUsuarioDao usuarioDao;
	
	public String primeraMayuscula(String s) {
		if(s.isBlank()) {
			return s;
		}
		return s.substring(0, 1).toUpperCase()+s.substring(1).toLowerCase();
	}
	public boolean usernameDisponible(String username) {
		return !usuarioDao.findByUsername(username).isPresent();
	}
	
	@Transactional
	public void registrarEstudiante(UsuarioRegistro u) {
		
		Usuario estudiante = new Usuario();
		estudiante.setUsername(u.getCarnet().toUpperCase());
		
		estudiante.setpNombre(primeraMayuscula(u.getpNombre()));
		estudiante.setsNombre(primeraMayuscula(u.getsNombre()));
		estudiante.setpApellido(primeraMayuscula(u.getpApellido()));
		estudiante.setsApellido(primeraMayuscula(u.getsApellido()));
		
		estudiante.setEmail(u.getCarnet().concat("@ues.edu.sv"));
		estudiante.setPassword(u.getPassword());
		estudiante.setActivo(true);
		estudiante.setEstudiante(true);
		usuarioDao.save(estudiante);
	}
	
	@Override
	@Transactional
	public void editarUsuarioAdmin(UsuarioEditarAdmin uea) {
		Usuario u = this.findById(uea.getId());
		
		
		u.setpNombre(primeraMayuscula(uea.getpNombre()));
		u.setsNombre(primeraMayuscula(uea.getsNombre()));
		u.setpApellido(primeraMayuscula(uea.getpApellido()));
		u.setsApellido(primeraMayuscula(uea.getsApellido()));
		if (u.getEstudiante()) {
			u.setUsername(uea.getCarnet().toUpperCase());
		} else {
			u.setUsername(uea.getUsername());
		}
		u.setEmail(uea.getEmail());
		usuarioDao.save(u);
	}
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	public Usuario findById(Long id) {
		Usuario u = usuarioDao.findById(id).orElse(null);
		return u;
	}
	@Override
	public UsuarioEditarAdmin usuarioEditarAdmin(Long id) { //dado el id retorna un usuario con los campos necesarios para editarlo
		
		Usuario u = this.findById(id);
		if(u==null) return null;
		UsuarioEditarAdmin uea = new UsuarioEditarAdmin();
		
		uea.setId(u.getId());
		uea.setpNombre(u.getpNombre());
		uea.setsNombre(u.getsNombre());
		uea.setpApellido(u.getpApellido());
		uea.setsApellido(u.getsApellido());
		uea.setEmail(u.getEmail());
		uea.setEstudiante(u.getEstudiante());
		if(uea.getEstudiante()) {
			uea.setCarnet(u.getUsername());
			uea.setUsername(null);
		}else {
			uea.setCarnet("XX99999");
			uea.setUsername(u.getUsername());
		}
		return uea;
	}
	@Override
	public List<Usuario> findAllDocentes() {
		
		return usuarioDao.findAllByEstudiante(false);
	}
	@Override
	public String getNombreCompleto(Long id) {
		
		return "no implementado";
	}
	@Override
	public List<Usuario> findAllDocentesActivos() {
		return usuarioDao.findAllByEstudianteFalseAndActivoTrue();
	}
	
	
	
}
