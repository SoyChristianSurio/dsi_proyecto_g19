package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoEvaluacion tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private MateriaImpartida asignatura;
	
	private Integer numeroEvaluacion;
	
	private Boolean activo;
	
	private Boolean repActivo;
	private Boolean revActivo;
	private Boolean difActivo;
	private Boolean camActivo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaEvaluacion;
	
	@OneToMany(
			mappedBy = "evaluacion",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<SolicitudRevision> solicitudesRevision;
	
	
	
	
	public Evaluacion() {
		this.solicitudesRevision=new ArrayList<SolicitudRevision>();
	}

	public List<SolicitudRevision> getSolicitudesRevision() {
		return solicitudesRevision;
	}

	public void setSolicitudesRevision(List<SolicitudRevision> solicitudesRevision) {
		this.solicitudesRevision = solicitudesRevision;
	}

	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}

//	public boolean addMatricula(Matricula m){
//		return this.matriculas.add(m);
//	}
//	
//	public List<Matricula> getMatriculas() {
//		return matriculas;
//	}
//
//	public void setMatriculas(List<Matricula> matriculas) {
//		this.matriculas = matriculas;
//	}
	
	
	
	public Integer getNumeroEvaluacion() {
		return numeroEvaluacion;
	}

	public Boolean getRepActivo() {
		return repActivo;
	}

	public void setRepActivo(Boolean repActivo) {
		this.repActivo = repActivo;
	}

	public Boolean getRevActivo() {
		return revActivo;
	}

	public void setRevActivo(Boolean revActivo) {
		this.revActivo = revActivo;
	}

	public Boolean getDifActivo() {
		return difActivo;
	}

	public void setDifActivo(Boolean difActivo) {
		this.difActivo = difActivo;
	}

	public Boolean getCamActivo() {
		return camActivo;
	}

	public void setCamActivo(Boolean camActivo) {
		this.camActivo = camActivo;
	}

	public void setNumeroEvaluacion(Integer numeroEvaluacion) {
		this.numeroEvaluacion = numeroEvaluacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEvaluacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvaluacion tipo) {
		this.tipo = tipo;
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

