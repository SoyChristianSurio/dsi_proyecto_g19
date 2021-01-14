package com.sigsaaqyf.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.GrupoTeo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Repository
public interface IGrupoTeoDao extends CrudRepository<GrupoTeo, Long> {

	public Optional<GrupoTeo> findByNumeroGrupo(Integer num);
	public GrupoTeo findByNumeroGrupoAndAsignatura(Integer gt, MateriaImpartida maimp);

}
