package com.sigsaaqyf.app.models.services;

import com.sigsaaqyf.app.models.entity.Evaluacion;
import com.sigsaaqyf.app.models.forms.EvaluacionEditar;

public interface IEvaluacionService {
	
	public Evaluacion findById(Long evaId);
	public boolean evaluacionYaExiste(EvaluacionEditar evaEdit);
	public Evaluacion guardar(EvaluacionEditar evaEdit);
}
