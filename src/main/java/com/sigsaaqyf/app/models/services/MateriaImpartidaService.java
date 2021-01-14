package com.sigsaaqyf.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IMateriaImpartidaDao;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Service
public class MateriaImpartidaService implements IMateriaImpartidaService {

	@Autowired
	IMateriaImpartidaDao maimpDao;
	@Autowired
	ICicloService cicloService;
	@Autowired
	IMateriaService materiaService;
	
	@Override
	public MateriaImpartida crear(Long idCiclo, Long idMateria) {
		MateriaImpartida matimp = new MateriaImpartida();
		matimp.setActivo(true);
		matimp.setCiclo(cicloService.findById(idCiclo));
		matimp.setMateria(materiaService.findById(idMateria));
		
		return maimpDao.save(matimp);
	}

	@Override
	public boolean cambiarEstado(Long idMaImp, boolean estado) {
		MateriaImpartida maimp = maimpDao.findById(idMaImp).orElse(null);
		if(maimp==null) return false;
		if(maimp.getActivo()==estado) return true;
		maimp.setActivo(estado);
		maimpDao.save(maimp);
		return true;
	}

	@Override
	public MateriaImpartida findById(Long id) {		
		return maimpDao.findById(id).orElse(null);
	}

}
