package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IMateriaDao;
import com.sigsaaqyf.app.models.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService {
	
	@Autowired
	IMateriaDao materiaDao;
	
	public Materia findById(Long id) {
		return materiaDao.findById(id).orElse(null);
	}

	@Override
	public List<Materia> findAll() {
		
		return (List<Materia>)materiaDao.findAll();
	}
	

}
