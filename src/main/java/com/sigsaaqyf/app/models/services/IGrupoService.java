package com.sigsaaqyf.app.models.services;

import com.sigsaaqyf.app.models.entity.GrupoDis;
import com.sigsaaqyf.app.models.entity.GrupoLab;
import com.sigsaaqyf.app.models.entity.GrupoTeo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

public interface IGrupoService {
	public GrupoTeo findTeoById(Long id);
	public GrupoDis findDisById(Long id);
	public GrupoLab findLabById(Long id);
	public GrupoTeo crearGt(GrupoTeo gt, Long maimpId);
	public GrupoDis crearGd(GrupoDis gd, Long maimpId);
	public GrupoLab crearGl(GrupoLab gl, Long maimpId);
	public GrupoTeo findTeoByNumeroGrupo(Integer num);
	public GrupoDis findDisByNumeroGrupo(Integer num);
	public GrupoLab findLabByNumeroGrupo(Integer num);
	public GrupoTeo findTeoByAsignatura(Integer num, MateriaImpartida maimp);
	public GrupoDis findDisByAsignatura(Integer num, MateriaImpartida maimp);
	public GrupoLab findLabByAsignatura(Integer num, MateriaImpartida maimp);
	public boolean asignarAGrupo(Long idg, Long idu, String tipoGrupo);
}
