package com.sigsaaqyf.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Ciclo;
@Repository
public interface ICicloDao extends CrudRepository<Ciclo, Long>  {
	
	public List<Ciclo> findAllByActivo(boolean activo);
	public Optional<Ciclo> findById(Long id); 
	public Optional<Ciclo> findByAnioAndSemestre(Integer a, Integer s);

}
