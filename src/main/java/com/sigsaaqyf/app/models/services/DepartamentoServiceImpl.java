package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IDepartamentoDao;
import com.sigsaaqyf.app.models.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	IDepartamentoDao departamentoDao;
	
	@Override
	public List<Departamento> findAll() {
		return (List<Departamento>)departamentoDao.findAll();
	}

	@Override
	public void save(Departamento d) {
		departamentoDao.save(d);
		
	}

}
