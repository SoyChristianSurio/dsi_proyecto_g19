package com.sigsaaqyf.app.models.services;

import java.util.List;

import com.sigsaaqyf.app.models.entity.Ciclo;
import com.sigsaaqyf.app.models.entity.Materia;

public interface ICicloService {
	public List<Ciclo> findAllByActivo(boolean a);
	public Ciclo findById(Long id);
	public Ciclo findByAnioAndSemestre(Integer anio, Integer semestre);
	public boolean save(Ciclo ciclo);
	public boolean update(Ciclo ciclo);
	public boolean activar(Long id);
	public boolean desactivar(Long id);
	public boolean eliminar(Long i);
	public List<Materia> materiasNoImpartidas(Long idCiclo);
	public List<Materia> materiasImpartidas(Long idCiclo);
}
