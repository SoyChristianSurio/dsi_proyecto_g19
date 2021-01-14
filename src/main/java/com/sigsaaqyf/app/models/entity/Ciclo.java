package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Ciclo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull (message = "El campo año es obligatorio")
	@Min(value = 2010, message = "valor mínimo: 2020")
	@Max(value = 2100, message = "valor maximo: 2100")
	private Integer anio;
	
	@NotNull(message = "debe seleccionar un semestre")
	private Integer semestre;
	
	private Boolean activo; 
	
	@OneToMany(
			mappedBy = "ciclo",
			fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.MERGE, 
					CascadeType.PERSIST})
	private List<MateriaImpartida> materiasImpartidas;
	
	//######################### METODOS ############################
	
	
	public List<MateriaImpartida> getMateriasImpartidas() {
		return materiasImpartidas;
	}
	public void setMateriasImpartidas(List<MateriaImpartida> materiasImpartidas) {
		this.materiasImpartidas = materiasImpartidas;
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
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	
	
	
}
