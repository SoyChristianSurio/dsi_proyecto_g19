package com.sigsaaqyf.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IEvaluacionDao;
import com.sigsaaqyf.app.models.dao.ITipoEvaluacionDao;
import com.sigsaaqyf.app.models.entity.Evaluacion;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.TipoEvaluacion;
import com.sigsaaqyf.app.models.forms.EvaluacionEditar;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

	@Autowired
	IEvaluacionDao evaluacionDao;
	@Autowired
	MateriaImpartidaService maimpService;
	@Autowired
	ITipoEvaluacionDao tipoEvaDao;
	
	
	@Override
	public boolean evaluacionYaExiste(EvaluacionEditar evaEdit) {
		
		TipoEvaluacion t = tipoEvaDao.findById(evaEdit.getTipo()).orElse(null);
		MateriaImpartida m = maimpService.findById(evaEdit.getAsignatura());
		
		Evaluacion eva = evaluacionDao.findByTipoAndNumeroEvaluacionAndAsignatura(t, evaEdit.getNumeroEvaluacion(), m);
		
		if(evaEdit.getId()==null) { 	//si el id es nulo, se está creando si no es que se esta editando
			if(eva==null) return false;
			else return true;
		}
		else {
			
			if(eva==null) return false;
			else {
				if(eva.getId()==evaEdit.getId()) return false;
				else return true;
			}
		}			
	}

	@Override
	public Evaluacion guardar(EvaluacionEditar evaEdit) {
		Evaluacion eva = new Evaluacion();
		if(evaEdit.getId()!=null) eva.setId(evaEdit.getId());
		eva.setActivo(true);
		eva.setDifActivo(evaEdit.getDifActivo());
		eva.setRevActivo(evaEdit.getRevActivo());
		eva.setRepActivo(evaEdit.getRepActivo());
		eva.setCamActivo(evaEdit.getCamActivo());
		eva.setAsignatura(maimpService.findById(evaEdit.getAsignatura()));
		eva.setTipo(tipoEvaDao.findById(evaEdit.getTipo()).orElse(null));
		eva.setNumeroEvaluacion(evaEdit.getNumeroEvaluacion());
		System.out.println("La fecha es man!!");
		eva.setFechaEvaluacion(evaEdit.getFechaEvaluacion());
		
		return evaluacionDao.save(eva);
	}

	@Override
	public Evaluacion findById(Long evaId) {
		return evaluacionDao.findById(evaId).orElse(null);
	}
}
