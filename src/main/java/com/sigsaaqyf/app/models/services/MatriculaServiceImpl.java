package com.sigsaaqyf.app.models.services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigsaaqyf.app.models.dao.IMatriculaDao;
import com.sigsaaqyf.app.models.entity.GrupoDis;
import com.sigsaaqyf.app.models.entity.GrupoLab;
import com.sigsaaqyf.app.models.entity.GrupoTeo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.Matricula;
import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.InfoMatriculaCsv;
import com.sigsaaqyf.app.models.forms.UsuarioCsv;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IMatriculaService matriculaService;
	@Autowired
	IMatriculaDao matriculaDao;
	@Autowired
	IMateriaImpartidaService maimpService;
	@Autowired
	IGrupoService grupoService;
	
	@Override
	public InfoMatriculaCsv matricularViaCsv(ArrayList<UsuarioCsv> alumnos, Long maimpId) {
		int registrados=0;
		int fallidos=0;
		int matriculados=0;
		InfoMatriculaCsv info = new InfoMatriculaCsv();
		MateriaImpartida maimp = maimpService.findById(maimpId);
		
		for(UsuarioCsv alumno : alumnos) {			
			
			
			
			
			Pattern pat = Pattern.compile("^[a-z A-Z]{2}[0-9]{5}$");			// Evaluar que el carnet sea válido
		    Matcher mat = pat.matcher(alumno.getCarnet());                      //                                                   
		    if (mat.matches()) {												// Si el carnet es valido continua el proceso
		    	System.out.println(alumno.getNumero());
		        Usuario u = usuarioService.findByUsername(alumno.getCarnet());
		        Matricula matric = null;
		        GrupoTeo gt=null;
	        	GrupoDis gd=null;
	        	GrupoLab gl=null;
	        	if(alumno.getGt()!= null) { gt = grupoService.findTeoByAsignatura(alumno.getGt(), maimp);}			// se consulta los grupos
	        	if(alumno.getGd()!= null) { gd = grupoService.findDisByAsignatura(alumno.getGd(), maimp);}			//
	        	if(alumno.getGl()!= null) { gl = grupoService.findLabByAsignatura(alumno.getGl(), maimp);}			//
	        	String msg = "registro:"+alumno.getNumero()+". ";
		        
	        	if(u==null) {	
	        		if(alumno.getpNombre()==null) {
	    				info.noMatriculadosAdd("registro:"+alumno.getNumero()+". No se proporciono primer nombre");
	    				continue;
	    			}
	    			if(alumno.getsNombre()==null) {
	    				alumno.setsNombre("");
	    			}
	    			if(alumno.getpApellido()==null) {
	    				info.noMatriculadosAdd("registro:"+alumno.getNumero()+". No se proporciono primer apellido");
	    				continue;
	    			}
	    			if(alumno.getsApellido()==null) {
	    				info.noMatriculadosAdd("registro:"+alumno.getNumero()+". No se proporciono segundo apellido");
	    				continue;
	    			}
	        																	// Si no existe el estudiante en la BD se crea a partir 
		        	u = new Usuario();											// de la informacion del archivo CSV.
		        	u.setUsername(alumno.getCarnet());							//
		        	u.setpNombre(alumno.getpNombre());							//
		        	u.setsNombre(alumno.getsNombre());							//
		        	u.setpApellido(alumno.getpApellido());						//
		        	u.setsApellido(alumno.getsApellido());						//
		        	u.setPassword(alumno.getCarnet().toUpperCase());			// Se establece el carnet como contraseña
		        	u=usuarioService.registrarEstudiante(u);
		        	
		        	info.registradosAdd("registro:"+alumno.getNumero()+			// Informe de creacion del respectivo registro del archivo csv
		        						". Usuario: "+u.getUsername()+
		        						" creado a partir de archivo CSV");
		        	
		        }
		        else {																			// Si existe el estudiante se comprueba si ya está matriculado o no
		        	matric = matriculaDao.findByAsignaturaAndEstudiante(maimp, u);	// 
		        	if(matric != null) {										// ACTUALIZAR MATRICULA EXISTENTE  
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGt()!= null) {
		        			if(gt!=null) {																			// Si existe el grupo
		        				if(matric.getGrupoTeo()==null) {
		        					msg = msg+"GT:null->"+alumno.getGt()+", ";
		        					matric.setGrupoTeo(gt);
		        				}
		        				else if(matric.getGrupoTeo().getNumeroGrupo() != alumno.getGt()) { 						// si el actual numero de GT es distinto al escrito en el CSV
			        				msg = msg+"GT:"+matric.getGrupoTeo().getNumeroGrupo()+"->"+alumno.getGt()+", ";
			        				matric.setGrupoTeo(gt);	// se cambia el estudiante al GT escrito en el CSV
			        			}
			        		}else {
			        			msg = msg+"GT:"+alumno.getGt()+" NoExiste, ";
			        		}
		        		}
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGd()!= null) {
			        		if(gd!=null) {																			// Si existe el grupo
			        			if(matric.getGrupoDis()==null) {
		        					msg = msg+"GD:null->"+alumno.getGd()+", ";
		        					matric.setGrupoDis(gd);
		        				}
		        				else if(matric.getGrupoDis().getNumeroGrupo() != alumno.getGd()) { 						// si el actual numero de GT es distinto al escrito en el CSV
			        				msg = msg+"GD:"+matric.getGrupoDis().getNumeroGrupo()+"->"+alumno.getGd()+", ";
			        				matric.setGrupoDis(gd);	// se cambia el estudiante al GT escrito en el CSV
			        			}
			        		}else {
			        			msg = msg+"GD:"+alumno.getGd()+" NoExiste, ";
			        		}
		        		}
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGl()!= null) {
			        		if(gl!=null) {																			// Si existe el grupo
			        			if(matric.getGrupoLab()==null) {
		        					msg = msg+"GL:null->"+alumno.getGl()+", ";
		        					matric.setGrupoLab(gl);
		        				}
		        				else if(matric.getGrupoLab().getNumeroGrupo() != alumno.getGl()) { 						// si el actual numero de GT es distinto al escrito en el CSV
			        				msg = msg+"GL:"+matric.getGrupoLab().getNumeroGrupo()+"->"+alumno.getGl()+", ";
			        				matric.setGrupoLab(gl);	// se cambia el estudiante al GT escrito en el CSV
			        			}
			        		}else {
			        			msg = msg+"GL:"+alumno.getGl()+" NoExiste. ";
			        		}
		        		}
		        		matriculaDao.save(matric);
		        		info.actualizadosAdd(msg);
		        		continue;
		        	}
		        	
		        }	//else {												// CREAR MATRICULA NUEVA
		        		//----------------------------------------------------------------------------------------
		        		matric = new Matricula();
		        		matric.setAsignatura(maimp);	// matricular en la materia en gestion
		        		matric.setEstudiante(u);		// al estudiante
		        		matric.setActivo(true);			// matricula activa
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGt()!= null) {
			        		if(gt!=null) {																			// Si existe el grupo
			        			matric.setGrupoTeo(gt);
		        				msg = msg+"GT:"+alumno.getGt()+", ";	// se cambia el estudiante al GT escrito en el CSV
		        			}
			        		else msg = msg+"GT:"+alumno.getGt()+" NoExiste, ";
		        		}
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGd()!= null) {
			        		if(gd!=null) {																			// Si existe el grupo
			        			matric.setGrupoDis(gd);	
		        				msg = msg+"GD:"+alumno.getGd()+", ";	// se cambia el estudiante al GT escrito en el CSV
		        			}else msg = msg+"GD:"+alumno.getGd()+" NoExiste, ";
		        		}
		        		//----------------------------------------------------------------------------------------
		        		if(alumno.getGl()!= null) {
			        		if(gl!=null) {																			// Si existe el grupo
			        			matric.setGrupoLab(gl);	
			        			msg = msg+"GL:"+alumno.getGl()+", ";	// se cambia el estudiante al GT escrito en el CSV
			        			
			        		}else msg = msg+"GL:"+alumno.getGl()+" NoExiste. ";
		        		}
		        		matriculaDao.save(matric);
		        		info.matriculadosAdd(msg);
		        	//}
				
		        
		    } else {
		    	System.out.println("NO"); //Borrar
		    	info.noMatriculadosAdd("registro:"+alumno.getNumero()+". Carnet no válido");
		    	fallidos++;
		    }
			
			
		}
		
		return info;
	}

	@Override
	public Matricula save(Matricula matricula) {
		return matriculaDao.save(matricula);
	}

	@Override
	public Matricula findById(Long id) {
		return matriculaDao.findById(id).orElse(null);
	}

}
