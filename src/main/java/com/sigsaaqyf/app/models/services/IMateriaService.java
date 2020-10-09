package com.sigsaaqyf.app.models.services;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Materia;
import com.sigsaaqyf.app.models.forms.MateriaEditar;

public interface IMateriaService {
	public Materia findById(Long id);
	public List<Materia> findAll();
	public MateriaEditar findByIdEdit(Long id);
	public void save(MateriaEditar me);
	public void deleteById(Long id);
}
