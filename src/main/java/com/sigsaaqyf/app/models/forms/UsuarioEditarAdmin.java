package com.sigsaaqyf.app.models.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UsuarioEditarAdmin {
	private Long id;
//---------------------------------------------------------------
	@Pattern(regexp = "^[a-z A-Z]{2}[0-9]{5}$", message = "carnet no válido")
	private String carnet;
//----------------------------------------------------------------
	private String username;
//----------------------------------------------------------------	
	@NotBlank(message = "El primer nombre es requerido")
	private String pNombre;
//----------------------------------------------------------------
	private String sNombre;   //campo opcional
//----------------------------------------------------------------	
	@NotBlank(message = "El primer apellido es requerido")
	private String pApellido;
//----------------------------------------------------------------	
	@NotBlank(message = "El segundo apellido es requerido")
	private String sApellido;
//----------------------------------------------------------------
	@Email(message = "El email no está bien formado")
	private String email;
//----------------------------------------------------------------
	private Boolean estudiante;
	
//########################## GETTERS y SETTERS ##############################
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	public String getpNombre() {
		return pNombre;
	}
	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}
	public String getsNombre() {
		return sNombre;
	}
	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}
	public String getpApellido() {
		return pApellido;
	}
	public void setpApellido(String pApellido) {
		this.pApellido = pApellido;
	}
	public String getsApellido() {
		return sApellido;
	}
	public void setsApellido(String sApellido) {
		this.sApellido = sApellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Boolean estudiante) {
		this.estudiante = estudiante;
	}
	
	
}
