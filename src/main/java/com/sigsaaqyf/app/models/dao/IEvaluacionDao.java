package com.sigsaaqyf.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.Evaluacion;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.TipoEvaluacion;

@Repository
public interface IEvaluacionDao extends CrudRepository<Evaluacion, Long>{

	public Evaluacion findByTipoAndNumeroEvaluacionAndAsignatura(TipoEvaluacion tipo, Integer numeroEvaluacion, MateriaImpartida asignatura);
	
}
