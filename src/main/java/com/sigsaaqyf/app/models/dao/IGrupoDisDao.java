package com.sigsaaqyf.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.GrupoDis;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Repository
public interface IGrupoDisDao extends CrudRepository<GrupoDis, Long> {

	public Optional<GrupoDis> findByNumeroGrupo(Integer num);
	public GrupoDis findByNumeroGrupoAndAsignatura(Integer gd, MateriaImpartida maimp);

}
