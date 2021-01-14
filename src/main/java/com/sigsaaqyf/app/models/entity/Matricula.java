package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario estudiante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GrupoTeo grupoTeo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GrupoDis grupoDis;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GrupoLab grupoLab;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private MateriaImpartida asignatura;
	
	private Boolean activo;

	/*
	 * @OneToMany( mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = {
	 * CascadeType.MERGE, CascadeType.PERSIST}) private List<GrupoTeo> teo;
	 * 
	 * 
	 * public boolean addTeo(GrupoTeo g){ return this.teo.add(g); }
	 * 
	 * public List<GrupoTeo> getTeo() { return teo; }
	 * 
	 * public void setTeo(List<GrupoTeo> teo) { this.teo = teo; }
	 */
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public MateriaImpartida getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(MateriaImpartida asignatura) {
		this.asignatura = asignatura;
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public GrupoTeo getGrupoTeo() {
		return grupoTeo;
	}

	public void setGrupoTeo(GrupoTeo grupoTeo) {
		this.grupoTeo = grupoTeo;
	}

	public GrupoDis getGrupoDis() {
		return grupoDis;
	}

	public void setGrupoDis(GrupoDis grupoDis) {
		this.grupoDis = grupoDis;
	}

	public GrupoLab getGrupoLab() {
		return grupoLab;
	}

	public void setGrupoLab(GrupoLab grupoLab) {
		this.grupoLab = grupoLab;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	
	
}

