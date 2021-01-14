package com.sigsaaqyf.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Materia;

@Repository
public interface IMateriaDao extends CrudRepository<Materia, Long>{
	
}
