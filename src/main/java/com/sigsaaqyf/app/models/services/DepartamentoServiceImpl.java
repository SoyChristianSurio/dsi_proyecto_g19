package com.sigsaaqyf.app.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IDepartamentoDao;
import com.sigsaaqyf.app.models.dao.JefaturaDao;
import com.sigsaaqyf.app.models.entity.Departamento;
import com.sigsaaqyf.app.models.entity.Jefatura;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	IDepartamentoDao departamentoDao;
	@Autowired
	JefaturaDao jefaturaDao;
	
	@Override
	public List<Departamento> findAll() {
		return (List<Departamento>)departamentoDao.findAll();
	}

	@Override
	public void save(Departamento d) {
		departamentoDao.save(d);
		
	}

	@Override
	public Departamento findById(Long id) {
		return departamentoDao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		departamentoDao.deleteById(id);
		
	}

	@Override
	public Jefatura terminarJefatura(Long id) {
		Departamento d = this.findById(id);
		Jefatura j = d.getJefatura();
		if(j != null) {
			j.setFechaFin(new Date());
			d.setJefatura(null);
			jefaturaDao.save(j);
			departamentoDao.save(d);
		}
		return j;
	}

}
