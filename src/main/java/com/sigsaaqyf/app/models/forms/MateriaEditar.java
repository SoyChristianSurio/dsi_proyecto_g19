package com.sigsaaqyf.app.models.forms;

import javax.validation.constraints.NotBlank;

public class MateriaEditar {
	
	private Long id;
	
	@NotBlank(message = "codigo requerido")
	private String codigo;
	@NotBlank(message = "nombre requerido")
	private String nombre;
	
	private Long departamentoid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getDepartamentoid() {
		return departamentoid;
	}
	public void setDepartamentoid(Long departamentoId) {
		this.departamentoid = departamentoId;
	}
	
	
}
