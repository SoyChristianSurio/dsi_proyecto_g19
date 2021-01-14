package com.sigsaaqyf.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Departamento;

@Repository
public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{
	
	
}
