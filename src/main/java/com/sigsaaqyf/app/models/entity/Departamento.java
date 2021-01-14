package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//---------------------------------------------------------
	@Column
	@NotBlank(message = "debe escribir un nombre")
	private String nombre;
//---------------------------------------------------------
	@OneToMany(mappedBy = "departamento",fetch = FetchType.LAZY)
	private List<Materia> materias;
//---------------------------------------------------------	
	@OneToOne(fetch = FetchType.LAZY )
	private Jefatura jefatura;
//---------------------------------------------------------	
	private static final long serialVersionUID = 1L;
	
//################## METODOS ####################
	
	
	
	public Departamento() {
		materias = new ArrayList<Materia>();
	}
	
	public Jefatura getJefatura() {
		return jefatura;
	}

	public void setJefatura(Jefatura jefatura) {
		this.jefatura = jefatura;
	}

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

	public void addMateria(Materia materia) {
		this.materias.add(materia);
	}

}
