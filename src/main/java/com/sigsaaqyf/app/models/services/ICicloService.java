package com.sigsaaqyf.app.models.services;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Ciclo;

public interface ICicloService {
	public List<Ciclo> findAllByActivo(boolean a);
	public Ciclo findById(Long id);
}
