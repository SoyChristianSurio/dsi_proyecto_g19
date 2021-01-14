package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;

import com.sigsaaqyf.app.models.entity.Matricula;
import com.sigsaaqyf.app.models.forms.InfoMatriculaCsv;
import com.sigsaaqyf.app.models.forms.UsuarioCsv;

public interface IMatriculaService {
	
	public Matricula findById(Long id);
	public InfoMatriculaCsv matricularViaCsv(ArrayList<UsuarioCsv> alumnos,Long maimp);
	public Matricula save(Matricula matricula);
}
