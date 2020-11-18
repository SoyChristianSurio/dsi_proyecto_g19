package com.sigsaaqyf.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.ICicloDao;
import com.sigsaaqyf.app.models.entity.Ciclo;

@Service
public class CicloServiceImpl implements ICicloService {
	@Autowired
	ICicloDao cicloDao;

	@Override
	public List<Ciclo> findAllByActivo(boolean a) {
		return cicloDao.findAllByActivo(a);
	}

	@Override
	public Ciclo findById(Long id) {
		return cicloDao.fidById(id);
	}
	
	
}
