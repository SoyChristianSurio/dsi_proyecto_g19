package com.sigsaaqyf.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//-------------------------------------------------------------
	@Column
	private String username;
	//-----------------------------
	@Column(name = "p_nombre")
	private String pNombre;
	//-----------------------------
	@Column(name = "s_nombre")
	private String sNombre;
	//-----------------------------
	@Column(name = "p_apellido")
	private String pApellido;
	//-----------------------------
	@Column(name = "s_apellido")
	private String sApellido;
//-------------------------------------------------------------
	@Column
	private String password;
//-------------------------------------------------------------
	@Column
	private String email;
//-------------------------------------------------------------
	@Column()
	private Boolean estudiante;
//-------------------------------------------------------------	
	@Column()
	private Boolean activo;
//-------------------------------------------------------------
	@Column(name = "fecha_registro")        // se nombra distinto en la BD.
	@Temporal(TemporalType.DATE)            // solo guardar la fecha en la BD, sin hora.
	@DateTimeFormat(pattern = "yyyy-MM-dd") // formato de la fecha
	private Date fechaRegistro;
//-------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
//METODOS
//###########################################################
	@PrePersist
	public void prePersist() {
		this.fechaRegistro=new Date();
	}
	
	public String getNombreCompleto() {
		if(this.sNombre==null) return this.pNombre.concat(" ").concat(this.pApellido).concat(" ").concat(this.sApellido);
		else return this.pNombre.concat(" ").concat(sNombre).concat(" ").concat(this.pApellido).concat(" ").concat(this.sApellido);
	} 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Boolean estudiante) {
		this.estudiante = estudiante;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
