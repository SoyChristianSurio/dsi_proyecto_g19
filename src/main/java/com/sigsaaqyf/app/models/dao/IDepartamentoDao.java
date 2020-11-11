package com.sigsaaqyf.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sigsaaqyf.app.models.entity.Departamento;
import com.sigsaaqyf.app.models.entity.Usuario;

public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{
	
	
}
