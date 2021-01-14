package com.sigsaaqyf.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.GrupoLab;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Repository
public interface IGrupoLabDao extends CrudRepository<GrupoLab, Long> {

	public Optional<GrupoLab> findByNumeroGrupo(Integer num);
	public GrupoLab findByNumeroGrupoAndAsignatura(Integer gl, MateriaImpartida maimp);

}
