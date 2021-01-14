package com.sigsaaqyf.app.models.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.SolicitudRevision;

@Repository
public interface IRevisionDao extends CrudRepository<SolicitudRevision, Long> {

	@Query(value = "SELECT sr.*" + 
			"FROM materia_impartida AS maimp " + 
			"JOIN ciclo AS c ON maimp.ciclo_id=c.id " + 
			"JOIN matricula AS matric ON maimp.id=matric.asignatura_id " + 
			"JOIN solicitudes_revision AS sr ON sr.matricula_id = matric.id " + 
			"JOIN usuarios AS us ON us.id=matric.estudiante_id " + 
			"WHERE c.activo=TRUE AND us.username=?1", nativeQuery = true)
	public ArrayList<SolicitudRevision> findAllActiveByUsername(String carnet);
}
