package com.sigsaaqyf.app.models.forms;

public class EstudianteDeGrupo {
	private String carnet;
	private String nombre;
	private Boolean estudianteActivo;
	private int gt;
	private int gd;
	private int gl;
	
	
	
	public EstudianteDeGrupo(String carnet, String nombre, Boolean estudianteActivo, int gt, int gd, int gl) {
		super();
		this.carnet = carnet;
		this.nombre = nombre;
		this.estudianteActivo = estudianteActivo;
		this.gt = gt;
		this.gd = gd;
		this.gl = gl;
	}
	
	public EstudianteDeGrupo() {

	}
	
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getEstudianteActivo() {
		return estudianteActivo;
	}
	public void setEstudianteActivo(Boolean estudianteActivo) {
		this.estudianteActivo = estudianteActivo;
	}
	public int getGt() {
		return gt;
	}
	public void setGt(int gt) {
		this.gt = gt;
	}
	public int getGd() {
		return gd;
	}
	public void setGd(int gd) {
		this.gd = gd;
	}
	public int getGl() {
		return gl;
	}
	public void setGl(int gl) {
		this.gl = gl;
	}
	
	
}
