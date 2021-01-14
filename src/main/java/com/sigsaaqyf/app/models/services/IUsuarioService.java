package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.UsuarioEditarAdmin;
import com.sigsaaqyf.app.models.forms.UsuarioRegistro;

@Service
public interface IUsuarioService {
	
	public List<Usuario> findAll();
	public void registrarEstudiante(UsuarioRegistro u);
	public Usuario registrarEstudiante(Usuario u);
	public void editarUsuarioAdmin(UsuarioEditarAdmin uea);
	public Usuario crearUsuarioAdmin(UsuarioEditarAdmin uea);
	public boolean usernameDisponible(String username);
	public boolean usernameDisponible(UsuarioEditarAdmin user);
	public Usuario findById(Long id);
	public Usuario findByUsername(String username);
	public UsuarioEditarAdmin usuarioEditarAdmin(Long id);
	public List<Usuario> findAllDocentes();
	public List<Usuario> findAllDocentesActivos();
	public String getNombreCompleto(Long id);
	public List<Usuario> findAllDocentesSinJefatura();
	
	
}
