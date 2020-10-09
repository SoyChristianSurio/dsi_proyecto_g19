package com.sigsaaqyf.app.models.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sigsaaqyf.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	 public Optional<Usuario> findByUsername(String username);
	 public List<Usuario> findAllByEstudiante(boolean b);
	 public List<Usuario> findAllByEstudianteFalseAndActivoTrue();
}
