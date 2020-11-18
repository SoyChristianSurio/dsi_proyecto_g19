package com.sigsaaqyf.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sigsaaqyf.app.models.entity.Ciclo;

public interface ICicloDao extends CrudRepository<Ciclo, Long>  {
	
	public List<Ciclo> findAllByActivo(boolean activo);
	public Ciclo fidById(Long id); 

}
