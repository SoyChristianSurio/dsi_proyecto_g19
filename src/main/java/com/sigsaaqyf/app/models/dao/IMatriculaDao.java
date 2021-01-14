package com.sigsaaqyf.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.Matricula;
import com.sigsaaqyf.app.models.entity.Usuario;

@Repository
public interface IMatriculaDao extends CrudRepository<Matricula, Long>{
	public Matricula findByAsignaturaAndEstudiante(MateriaImpartida asig, Usuario usu);
	
	@Query(value = "SELECT matric.*\r\n" + 
			"FROM materia_impartida AS maimp \r\n" + 
			"JOIN materias AS mat ON maimp.materia_id=mat.id\r\n" + 
			"JOIN ciclo AS c ON maimp.ciclo_id=c.id \r\n" + 
			"JOIN matricula AS matric ON maimp.id=matric.asignatura_id\r\n" + 
			"JOIN usuarios AS us ON us.id=matric.estudiante_id\r\n" + 
			"WHERE c.activo=TRUE AND us.username=?1",nativeQuery = true)
	public List<Matricula> findAllMatriculasActivasByUsername(String username);
}
