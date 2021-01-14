package com.sigsaaqyf.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IGrupoDisDao;
import com.sigsaaqyf.app.models.dao.IGrupoLabDao;
import com.sigsaaqyf.app.models.dao.IGrupoTeoDao;
import com.sigsaaqyf.app.models.entity.GrupoDis;
import com.sigsaaqyf.app.models.entity.GrupoLab;
import com.sigsaaqyf.app.models.entity.GrupoTeo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.Usuario;



@Service
public class GrupoServiceImpl implements IGrupoService {
	@Autowired
	IGrupoTeoDao gtDao;
	@Autowired
	IGrupoDisDao gdDao;
	@Autowired
	IGrupoLabDao glDao;
	@Autowired
	IMateriaImpartidaService maimpService;
	@Autowired
	IUsuarioService usuarioService;
	
	
//########################################################### Encontrar por Id
	@Override
	public GrupoTeo findTeoById(Long id) {
		return gtDao.findById(id).orElse(null);
	}
	@Override
	public GrupoDis findDisById(Long id) {
		return gdDao.findById(id).orElse(null);
	}
	@Override
	public GrupoLab findLabById(Long id) {
		return glDao.findById(id).orElse(null);
	}
//########################################################### Crear Grupo
	@Override
	public GrupoTeo crearGt(GrupoTeo gt, Long maimpId) {
		gt.setId(null);
		gt.setAsignatura(maimpService.findById(maimpId));
		gt.setActivo(true);
		return gtDao.save(gt);
	}
	@Override
	public GrupoDis crearGd(GrupoDis gd, Long maimpId) {
		gd.setId(null);
		gd.setAsignatura(maimpService.findById(maimpId));
		gd.setActivo(true);
		return gdDao.save(gd);
	}
	@Override
	public GrupoLab crearGl(GrupoLab gl, Long maimpId) {
		gl.setId(null);
		gl.setAsignatura(maimpService.findById(maimpId));
		gl.setActivo(true);
		return glDao.save(gl);
	}
//########################################################### Encontrar por Número de grupo
	@Override
	public GrupoTeo findTeoByNumeroGrupo(Integer num) {
		return gtDao.findByNumeroGrupo(num).orElse(null);
	}
	@Override
	public GrupoDis findDisByNumeroGrupo(Integer num) {
		return gdDao.findByNumeroGrupo(num).orElse(null);
	}
	@Override
	public GrupoLab findLabByNumeroGrupo(Integer num) {
		return glDao.findByNumeroGrupo(num).orElse(null);
	}
	
	@Override
	public GrupoTeo findTeoByAsignatura(Integer num, MateriaImpartida maimp) {
		return gtDao.findByNumeroGrupoAndAsignatura(num, maimp);
	}
	@Override
	public GrupoDis findDisByAsignatura(Integer num, MateriaImpartida maimp) {
		return gdDao.findByNumeroGrupoAndAsignatura(num, maimp);
	}
	@Override
	public GrupoLab findLabByAsignatura(Integer num, MateriaImpartida maimp) {
		return glDao.findByNumeroGrupoAndAsignatura(num, maimp);
	}
	
	@Override
	public boolean asignarAGrupo(Long idg, Long idu, String tipoGrupo) {
		Usuario user = usuarioService.findById(idu);
		if(user==null) return false;
		
		switch (tipoGrupo) {
		case "gt":
			GrupoTeo teo = this.findTeoById(idg);
			if(teo==null)return false;
			if(user.getEstudiante()) teo.setRepresentante(user);
			else teo.setDocente(user);
			gtDao.save(teo);
			break;
		
		case "gd":
			GrupoDis dis = this.findDisById(idg);
			if(dis==null)return false;
			if(!user.getEstudiante())dis.setDocente(user);
			gdDao.save(dis);
			break;
		
		case "gl":
			GrupoLab lab = this.findLabById(idg);
			if(lab==null)return false;
			if(!user.getEstudiante())lab.setDocente(user);
			glDao.save(lab);
			break;
		default:
			return false;
			
		}
		return true;
	}
}
	
