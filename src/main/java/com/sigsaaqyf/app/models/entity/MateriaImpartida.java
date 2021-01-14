package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MateriaImpartida implements Serializable {
		private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciclo ciclo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Materia materia;
	
	private Boolean activo;
	
	@OneToMany(
			mappedBy = "asignatura",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<GrupoTeo> teo;
	
	@OneToMany(
			mappedBy = "asignatura",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<GrupoDis> dis;
	
	@OneToMany(
			mappedBy = "asignatura",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<GrupoLab> lab;
	
	@OneToMany(
			mappedBy = "asignatura",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<Evaluacion> eva;
	
	@OneToMany(
			mappedBy = "asignatura",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<Matricula> matriculas;
	
	
	
	
	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Evaluacion> getEva() {
		return eva;
	}

	public void setEva(List<Evaluacion> eva) {
		this.eva = eva;
	}

	public boolean addTeo(GrupoTeo g){
		return this.teo.add(g);
	}
	
	public boolean addDis(GrupoDis g){
		return this.dis.add(g);
	}
	
	public boolean addLab(GrupoLab g){
		return this.lab.add(g);
	}
	
	public List<GrupoTeo> getTeo() {
		return teo;
	}

	public void setTeo(List<GrupoTeo> teo) {
		this.teo = teo;
	}

	public List<GrupoDis> getDis() {
		return dis;
	}

	public void setDis(List<GrupoDis> dis) {
		this.dis = dis;
	}

	public List<GrupoLab> getLab() {
		return lab;
	}

	public void setLab(List<GrupoLab> lab) {
		this.lab = lab;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
}

