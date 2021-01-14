package com.sigsaaqyf.app.models.forms;


public class UsuarioCsv {

	private String numero;
	
	private String pNombre;
	
	private String sNombre;
	
	private String pApellido;
	
	private String sApellido;
	
	private String carnet;
	
	private Integer gt;
	
	private Integer gd;
	
	private Integer gl;
	
	public UsuarioCsv(String pNombre, String sNombre, String pApellido, String sApellido, String carnet, Integer gt,
			Integer gd, Integer gl) {
		this.pNombre = pNombre;
		this.sNombre = sNombre;
		this.pApellido = pApellido;
		this.sApellido = sApellido;
		this.carnet = carnet;
		this.gt = gt;
		this.gd = gd;
		this.gl = gl;
	}
	
	public UsuarioCsv() {
		
	}
	
	
	
	
	

	
	
	@Override
	public String toString() {
		return "UsuarioCsv [numero=" + numero + ", pNombre=" + pNombre + ", sNombre=" + sNombre + ", pApellido="
				+ pApellido + ", sApellido=" + sApellido + ", carnet=" + carnet + ", gt=" + gt + ", gd=" + gd + ", gl="
				+ gl + "]";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	public Integer getGt() {
		return gt;
	}
	public void setGt(Integer gt) {
		this.gt = gt;
	}
	public Integer getGd() {
		return gd;
	}
	public void setGd(Integer gd) {
		this.gd = gd;
	}
	public Integer getGl() {
		return gl;
	}
	public void setGl(Integer gl) {
		this.gl = gl;
	}
	
	
}
