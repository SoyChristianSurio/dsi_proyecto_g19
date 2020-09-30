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
	public void editarUsuarioAdmin(UsuarioEditarAdmin uea);
	public boolean usernameDisponible(String username);
	public Usuario findById(Long id);
	public UsuarioEditarAdmin usuarioEditarAdmin(Long id);
	
	
	
}
