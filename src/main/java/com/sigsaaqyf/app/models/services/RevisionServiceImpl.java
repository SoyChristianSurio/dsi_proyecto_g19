package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IRevisionDao;
import com.sigsaaqyf.app.models.entity.SolicitudRevision;

@Service
public class RevisionServiceImpl implements IRevisionService {

	@Autowired
	IRevisionDao revisionDao;
	@Autowired
	IMatriculaService matriculaService;
	@Autowired
	IEvaluacionService evaluacionService;
	
	
	@Override
	public SolicitudRevision guardar(Long idMatr, Long idEva) {
		SolicitudRevision sr = new SolicitudRevision();
		sr.setEvaluacion(evaluacionService.findById(idEva));
		sr.setMatricula(matriculaService.findById(idMatr));
		
		return revisionDao.save(sr);
	}


	@Override
	public ArrayList<SolicitudRevision> findAllActiveByUsername(String carnet) {
		return revisionDao.findAllActiveByUsername(carnet);
	}


	@Override
	public SolicitudRevision findById(Long idRev) {
		return revisionDao.findById(idRev).orElse(null);
	}


	@Override
	public SolicitudRevision save(SolicitudRevision sol) {
		return revisionDao.save(sol);
	}

}
