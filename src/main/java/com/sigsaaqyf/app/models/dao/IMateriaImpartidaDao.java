package com.sigsaaqyf.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Repository
public interface IMateriaImpartidaDao extends CrudRepository<MateriaImpartida, Long> {
	public List<MateriaImpartida> findAllByCiclo(Long id);
	
}
