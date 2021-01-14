package com.sigsaaqyf.app.models.forms;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class EvaluacionEditar {
	
	private Long id;
	
	@NotNull(message = "Campo obligatorio")
	private Long tipo;
	
	
	private Long asignatura;
	
	@NotNull(message = "Campo obligatorio")
	private Integer numeroEvaluacion;
	
	private Boolean activo;
	private Boolean repActivo;
	private Boolean revActivo;
	private Boolean difActivo;
	private Boolean camActivo;
	
	@NotNull(message = "La feha es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaEvaluacion;

	
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Long asignatura) {
		this.asignatura = asignatura;
	}

	public Integer getNumeroEvaluacion() {
		return numeroEvaluacion;
	}

	public void setNumeroEvaluacion(Integer numeroEvaluacion) {
		this.numeroEvaluacion = numeroEvaluacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	
	
	
}
