package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "solicitudes_revision")
public class SolicitudRevision implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean aprobado;
	
	private String msg;
	
	@Column(name = "fecha_cita")        			// se nombra distinto en la BD.
	@Temporal(TemporalType.TIMESTAMP)            		// solo guardar la fecha en la BD, sin hora.
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 	// formato de la fecha
	private Date fechaCita;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Matricula matricula;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Evaluacion evaluacion;
	
	@Column(name = "fecha_registro")        			// se nombra distinto en la BD.
	@Temporal(TemporalType.TIMESTAMP)            		// solo guardar la fecha en la BD, sin hora.
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 	// formato de la fecha
	private Date fechaRegistro;
	

	@PrePersist
	public void prePersist() {
		this.fechaRegistro=new Date();
	}
	
	public Date getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getAprobado() {
		return aprobado;
	}
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}


	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public Matricula getMatricula() {
		return matricula;
	}	
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
