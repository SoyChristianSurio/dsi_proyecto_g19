package com.sigsaaqyf.app.models.forms;

import java.util.ArrayList;

public class InfoMatriculaCsv {

	private ArrayList<String> actualizados;
	private ArrayList<String> matriculados;
	private ArrayList<String> noMatriculados;
	private ArrayList<String> registrados;
	
	public InfoMatriculaCsv() {
		this.actualizados = new ArrayList<String>();
		this.noMatriculados = new ArrayList<String>();
		this.matriculados = new ArrayList<String>();
		this.registrados = new ArrayList<String>();
	}
	
	
	
	public void matriculadosAdd(String msg) {
		this.matriculados.add(msg);
	}
	public ArrayList<String> getMatriculados() {
		return matriculados;
	}
	public void setMatriculados(ArrayList<String> matriculados) {
		this.matriculados = matriculados;
	}
	
	public void registradosAdd(String msg) {
		this.registrados.add(msg);
	}
	public ArrayList<String> getRegistrados() {
		return registrados;
	}
	public void setRegistrados(ArrayList<String> registrados) {
		this.registrados = registrados;
	}
	
	public void actualizadosAdd(String msg) {
		this.actualizados.add(msg);
	}
	public ArrayList<String> getActualizados() {
		return actualizados;
	}
	public void setActualizados(ArrayList<String> yaMatriculados) {
		this.actualizados = yaMatriculados;
	}
	
	public void noMatriculadosAdd(String msg) {
		this.noMatriculados.add(msg);
	}
	public ArrayList<String> getNoMatriculados() {
		return noMatriculados;
	}
	public void setNoMatriculados(ArrayList<String> noMatriculados) {
		this.noMatriculados = noMatriculados;
	}
	 
	 
}
