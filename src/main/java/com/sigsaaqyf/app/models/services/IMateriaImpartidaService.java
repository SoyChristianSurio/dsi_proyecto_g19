package com.sigsaaqyf.app.models.services;

import com.sigsaaqyf.app.models.entity.MateriaImpartida;

public interface IMateriaImpartidaService {
	public MateriaImpartida crear(Long idCiclo, Long idMateria);
	public boolean cambiarEstado(Long idMaImp, boolean estado);
	public MateriaImpartida findById(Long id);
}
