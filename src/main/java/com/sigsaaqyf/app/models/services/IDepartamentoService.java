package com.sigsaaqyf.app.models.services;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Departamento;
import com.sigsaaqyf.app.models.entity.Jefatura;

public interface IDepartamentoService {
		
	public List<Departamento> findAll();
	public void save(Departamento d);
	public Departamento findById(Long id);
	public void eliminar(Long id);
	public Jefatura terminarJefatura(Long id);
	
}
