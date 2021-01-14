package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.ICicloDao;
import com.sigsaaqyf.app.models.entity.Ciclo;
import com.sigsaaqyf.app.models.entity.Materia;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;

@Service
public class CicloServiceImpl implements ICicloService {
	@Autowired
	ICicloDao cicloDao;
	@Autowired
	IMateriaService materiaService;
	
	
	@Override
	public List<Ciclo> findAllByActivo(boolean a) {
		return cicloDao.findAllByActivo(a);
	}

	@Override
	public Ciclo findById(Long id) {
		return cicloDao.findById(id).orElse(null);
	}

	@Override
	public Ciclo findByAnioAndSemestre(Integer anio, Integer semestre) {
		
		return cicloDao.findByAnioAndSemestre(anio, semestre).orElse(null);
	}

	@Override
	public boolean save(Ciclo ciclo) {
		if(this.findByAnioAndSemestre(ciclo.getAnio(), ciclo.getSemestre())!=null) {
			return false;
		}
		Ciclo cicloActivo = this.findAllByActivo(true).get(0);// Buscar ciclo activo
		if(ciclo.getActivo()==true && cicloActivo!=null) {    // Si el nuevo ciclo se activará
			cicloActivo.setActivo(false);					  // desactivar el ciclo activo encontrado
			cicloDao.save(cicloActivo);
		}
		cicloDao.save(ciclo);
		return true;
	}

	@Override
	public boolean update(Ciclo ciclo) {
		Ciclo cicloActivo = this.findByAnioAndSemestre(ciclo.getAnio(), ciclo.getSemestre());
		if(cicloActivo!=null && ciclo.getId()!=cicloActivo.getId()) { // comprobar que no existe otro ciclo con los mismos datos
			return false;
		}
		
		cicloActivo = this.findAllByActivo(true).get(0);      // Buscar ciclo activo
		if(ciclo.getActivo()==true && cicloActivo!=null) {    // Si el nuevo ciclo se activará
			cicloActivo.setActivo(false);					  // desactivar el ciclo activo encontrado
			cicloDao.save(cicloActivo);
		}
		cicloDao.save(ciclo);
		return true;
	}

	@Override
	public boolean activar(Long id) {
		Ciclo ciclo = cicloDao.findById(id).orElse(null);
		if(ciclo==null) return false;
		
		for(Ciclo c:this.findAllByActivo(true)) c.setActivo(false);
		
		ciclo.setActivo(true);
		cicloDao.save(ciclo);
		return true;
	}

	@Override
	public boolean desactivar(Long id) {
		Ciclo ciclo = cicloDao.findById(id).orElse(null);
		if(ciclo==null) return false;
		ciclo.setActivo(false);
		cicloDao.save(ciclo);
		return true;
	}

	@Override
	public boolean eliminar(Long i) {
		cicloDao.deleteById(i);
		return false;
	}

	@Override
	public List<Materia> materiasNoImpartidas(Long idCiclo) {
		Ciclo ciclo = this.findById(idCiclo);
		if(ciclo.getMateriasImpartidas().isEmpty()) return materiaService.findAll();
		
		List<Materia> materiasNoImpartidas = new ArrayList<Materia>();
		
		for(Materia materia: materiaService.findAll()) {
			int i=0;
			for(MateriaImpartida materiac:ciclo.getMateriasImpartidas()) {
				
				if(materia.getId()==materiac.getMateria().getId()) i=1;
				if(i==1) break;
				
			}
			if(i==0) materiasNoImpartidas.add(materia);
		}
		return materiasNoImpartidas;
	}

	@Override
	public List<Materia> materiasImpartidas(Long idCiclo) {
		
		List<Materia> materiasImpartidas = new ArrayList<Materia>();
		for(MateriaImpartida m : findById(idCiclo).getMateriasImpartidas()) {
			materiasImpartidas.add(m.getMateria());
		}
		
		return materiasImpartidas;
	}
	
	
}
