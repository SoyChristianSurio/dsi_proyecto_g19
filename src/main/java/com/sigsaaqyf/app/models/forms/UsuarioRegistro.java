package com.sigsaaqyf.app.models.forms;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UsuarioRegistro {
	
	@Pattern(regexp = "^[a-z A-Z]{2}[0-9]{5}$", message = "carnet no válido")
	private String carnet;
	@NotBlank(message = "El primer nombre es requerido")
	private String pNombre;
	
	private String sNombre;   //campo opcional
	@NotBlank(message = "El primer apellido es requerido")
	private String pApellido;
	@NotBlank(message = "El segundo apellido es requerido")
	private String sApellido;
	
	@NotBlank(message = "La contraseña no puede quedar vacía")
	private String password;
	
	private String password2; 

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	
}
