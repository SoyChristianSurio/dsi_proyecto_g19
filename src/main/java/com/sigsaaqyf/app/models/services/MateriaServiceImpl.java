package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigsaaqyf.app.models.dao.IMateriaDao;
import com.sigsaaqyf.app.models.entity.Materia;
import com.sigsaaqyf.app.models.forms.MateriaEditar;

@Service
public class MateriaServiceImpl implements IMateriaService {
	
	@Autowired
	IMateriaDao materiaDao;
	@Autowired
	IDepartamentoService departamentoService;
	
	public Materia findById(Long id) {
		return materiaDao.findById(id).orElse(null);
	}

	@Override
	public List<Materia> findAll() {
		
		return (List<Materia>)materiaDao.findAll();
	}

	@Override
	public MateriaEditar findByIdEdit(Long id) {
		Materia m = materiaDao.findById(id).orElse(null);
		MateriaEditar me = new MateriaEditar();
		
		me.setId(m.getId());
		me.setCodigo(m.getCodigo());
		me.setNombre(m.getNombre());
		if(m.getDepartamento()==null) me.setDepartamentoid(null);
		else me.setDepartamentoid(m.getDepartamento().getId());
		
		return me;
	}

	@Transactional
	@Override
	public void save(MateriaEditar me) {
		Materia m = new Materia();
		m.setId(me.getId());
		m.setCodigo(me.getCodigo());
		m.setNombre(me.getNombre());
		m.setDepartamento(departamentoService.findById(me.getDepartamentoid()));
		
		materiaDao.save(m);
		
	}

	@Override
	public void deleteById(Long id) {
		materiaDao.deleteById(id);
		
	}
	

}
