package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Entity
@Table(name = "jefaturas")
public class Jefatura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_registro")        
	@Temporal(TemporalType.TIMESTAMP)            
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRegistro; 
	
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaFin;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario jefe;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Departamento departamento;
	
	private static final long serialVersionUID = 1L;

	@PrePersist
	public void prePersist() {
		this.fechaRegistro=new Date();
	} 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Usuario getJefe() {
		return jefe;
	}

	public void setJefe(Usuario jefe) {
		this.jefe = jefe;
	}
	
	
	
}
