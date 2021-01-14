package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;

import com.sigsaaqyf.app.models.entity.SolicitudRevision;

public interface IRevisionService {
	public SolicitudRevision guardar(Long idMatr, Long idEva);
	public ArrayList<SolicitudRevision> findAllActiveByUsername(String carnet);
	public SolicitudRevision findById(Long idRev);
	public SolicitudRevision save(SolicitudRevision sol);
}
