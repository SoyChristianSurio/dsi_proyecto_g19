package com.sigsaaqyf.app.models.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.JefaturaDao;
import com.sigsaaqyf.app.models.entity.Departamento;
import com.sigsaaqyf.app.models.entity.Jefatura;

@Service
public class JefaturaServiceImpl implements IJefaturaService {
	
	@Autowired
	IDepartamentoService departamentoService;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	JefaturaDao jefaturaDao;
	
	@Override
	public Jefatura nueva(Long departamentoid, Long usuarioid) {
		
		Jefatura jefatura = new Jefatura();
		Departamento dep  = departamentoService.findById(departamentoid);
		if(dep.getJefatura()!=null) {
			dep.getJefatura().setFechaFin(new Date());
			departamentoService.save(dep);
		}
		
		jefatura.setDepartamento(dep);//---------------------------------- Asignar departamento 
		jefatura.setJefe(usuarioService.findById(usuarioid));//----------- Asignar jefe
		jefatura = jefaturaDao.save(jefatura);
		dep.setJefatura(jefatura);//-------------------------------------- Asignar jefatura recien creada al departamento
		departamentoService.save(dep);
		
		return jefatura;
	}

}
