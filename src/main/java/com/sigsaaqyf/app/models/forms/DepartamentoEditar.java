package com.sigsaaqyf.app.models.forms;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Materia;

public class DepartamentoEditar {
	private Long id;
	private String nombre;
	private List<Materia> materias;
	private Long jefatura;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	public Long getJefatura() {
		return jefatura;
	}
	public void setJefatura(Long jefatura) {
		this.jefatura = jefatura;
	}
	
	
}
