package com.sigsaaqyf.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.JefaturaDao;
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
		jefatura.setDepartamento(departamentoService.findById(departamentoid));
		jefatura.setJefe(usuarioService.findById(usuarioid));
		jefatura = jefaturaDao.save(jefatura);
		departamentoService.findById(departamentoid).setJefatura(jefatura);
		return jefatura;
	}

}
