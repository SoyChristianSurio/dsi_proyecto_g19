package com.sigsaaqyf.app.models.services;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Materia;

public interface IMateriaService {
	public Materia findById(Long id);
	public List<Materia> findAll();
}
