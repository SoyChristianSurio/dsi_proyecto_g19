package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class GrupoDis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario Docente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private MateriaImpartida asignatura;
	
	private Boolean activo;
	
	@OneToMany(mappedBy = "grupoDis",	fetch = FetchType.LAZY,	cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Matricula> matriculas;

	@Column(name = "numero_grupo")
	@NotNull
	@Min(value = 1, message = "El numero debe ser 1 o superior")
	private Integer numeroGrupo;
	
	
	
	public Integer getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(Integer numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	public GrupoDis() {
		this.matriculas = new ArrayList<Matricula>();
	}

	public boolean addMatricula(Matricula m){
		return this.matriculas.add(m);
	}
	
	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getDocente() {
		return Docente;
	}

	public void setDocente(Usuario docente) {
		Docente = docente;
	}

	public MateriaImpartida getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(MateriaImpartida asignatura) {
		this.asignatura = asignatura;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}

