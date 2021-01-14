package com.sigsaaqyf.app.controllers;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sigsaaqyf.app.models.dao.ITipoEvaluacionDao;
import com.sigsaaqyf.app.models.entity.Evaluacion;
import com.sigsaaqyf.app.models.entity.GrupoDis;
import com.sigsaaqyf.app.models.entity.GrupoLab;
import com.sigsaaqyf.app.models.entity.GrupoTeo;
import com.sigsaaqyf.app.models.entity.MateriaImpartida;
import com.sigsaaqyf.app.models.entity.Matricula;
import com.sigsaaqyf.app.models.entity.SolicitudRevision;
import com.sigsaaqyf.app.models.entity.Usuario;
import com.sigsaaqyf.app.models.forms.EstudianteDeGrupo;
import com.sigsaaqyf.app.models.forms.EvaluacionEditar;
import com.sigsaaqyf.app.models.forms.InfoMatriculaCsv;
import com.sigsaaqyf.app.models.forms.UsuarioCsv;
import com.sigsaaqyf.app.models.services.ICicloService;
import com.sigsaaqyf.app.models.services.IEvaluacionService;
import com.sigsaaqyf.app.models.services.IGrupoService;
import com.sigsaaqyf.app.models.services.IMateriaImpartidaService;
import com.sigsaaqyf.app.models.services.IMateriaService;
import com.sigsaaqyf.app.models.services.IMatriculaService;
import com.sigsaaqyf.app.models.services.IRevisionService;
import com.sigsaaqyf.app.models.services.IUsuarioService;

@Controller
@RequestMapping("/asignatura")
public class MateriaImpartidaController {

	@Autowired
	ICicloService cicloService;
	@Autowired
	IMateriaService materiaService;
	@Autowired
	IMateriaImpartidaService materiaImpService;
	@Autowired
	IGrupoService grupoService;
	@Autowired
	ITipoEvaluacionDao tipoEvaDao;
	@Autowired
	IEvaluacionService evaluacionService;
	@Autowired
	IMatriculaService matriculaService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IRevisionService revisionService;
	
	@GetMapping("/gestionar/{id}")
	public String gestionarG(@PathVariable (name = "id")Long idMaImp, Model model) {//
		MateriaImpartida maimp = materiaImpService.findById(idMaImp);				//
		if(maimp==null) return "asignatura/gestionar";								//	
		model.addAttribute("newTeo", new GrupoTeo());								//
		model.addAttribute("newDis", new GrupoDis());								//
		model.addAttribute("newLab", new GrupoLab());								//
		model.addAttribute("maimpGes", maimp);										//
		return "asignatura/gestionar";												//
	}
	
//############################################################# ACTIVAR ###################################################################
	
	@GetMapping("/activar/{id}")
	public String activarG(@PathVariable(name = "id")Long idMaImp, Model model, RedirectAttributes flash) {
		if(materiaImpService.cambiarEstado(idMaImp, true))flash.addFlashAttribute("success", "materia activada");
		else flash.addFlashAttribute("error", "no se pudo activar materia");
		return "redirect:/ciclo/activo";
	}
//############################################################ DESACTIVAR ##################################################################
	
	@GetMapping("/desactivar/{id}")
	public String desactivarG(@PathVariable(name = "id")Long idMaImp, Model model, RedirectAttributes flash) {
		if(materiaImpService.cambiarEstado(idMaImp, false))flash.addFlashAttribute("info", "materia desactivada");
		else flash.addFlashAttribute("error", "no se pudo desactivar materia");
		return "redirect:/ciclo/activo";
	}
//############################################################# ELIMINAR ###################################################################
	
	@GetMapping("/eliminar/{id}")
	public String eliminarG(Model model, RedirectAttributes flash) {
		flash.addFlashAttribute("error", "vengo del boton eliminar");
		return "redirect:/ciclo/activo";
	}
	
//############################################################### CREAR GRUPO TEÓRICO #################################################################
	
	@PostMapping("/{id}/gt/crear")
	public String gtCrearG( @Valid @ModelAttribute("newTeo")GrupoTeo newTeo, BindingResult result, 
							@PathVariable(name = "id")Long idMaImp, 
							Model model, RedirectAttributes flash) {
		
		MateriaImpartida maimp = materiaImpService.findById(idMaImp);
		model.addAttribute("newDis", new GrupoDis());
		model.addAttribute("newLab", new GrupoLab());
		model.addAttribute("maimpGes", maimp);
		model.addAttribute("tabActiva", "teo");
		if(result.hasErrors()) {
			model.addAttribute("errorCrearGrupo", "teo"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		if(grupoService.findTeoByAsignatura(newTeo.getNumeroGrupo(),maimp)!=null) {
			model.addAttribute("otroError", "Ese numero de grupo ya existe");
			model.addAttribute("errorCrearGrupo", "teo"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		
		if(grupoService.crearGt(newTeo, idMaImp)==null) flash.addFlashAttribute("error", "No se pudo crear grupo teórico");
		else flash.addFlashAttribute("success", "Grupo teórico creado exitosamente");
		flash.addFlashAttribute("tabActiva", "teo");
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
	
	@GetMapping("/gestionar/{id}/gt/{idg}")
	public String gtGestionar(@PathVariable(name = "id")Long idMaImp, @PathVariable(name = "idg")Long idGt, Model model, RedirectAttributes flash) {
		GrupoTeo gt = grupoService.findTeoById(idGt);
		ArrayList<EstudianteDeGrupo> alumnos = new ArrayList<EstudianteDeGrupo>();
		for(Matricula m:gt.getMatriculas()) {
			EstudianteDeGrupo e = new EstudianteDeGrupo();
			e.setCarnet(m.getEstudiante().getUsername());
			e.setNombre(m.getEstudiante().getNombreCompleto());
			e.setEstudianteActivo(m.getEstudiante().getActivo());
			if(m.getGrupoTeo()!=null) e.setGt(m.getGrupoTeo().getNumeroGrupo());
			if(m.getGrupoDis()!=null) e.setGd(m.getGrupoDis().getNumeroGrupo());
			if(m.getGrupoLab()!=null) e.setGl(m.getGrupoLab().getNumeroGrupo());
			alumnos.add(e);
		}
		flash.addFlashAttribute("tituloLista", "Grupo Teórico "+gt.getNumeroGrupo());
		flash.addFlashAttribute("estudiantes", alumnos);
		flash.addFlashAttribute("tabActiva", "teo");
		flash.addFlashAttribute("gtid", gt.getId());
		String msg = (String) model.getAttribute("myMsg");
		if(msg!=null)flash.addFlashAttribute("info", msg);
		if(gt.getDocente()!=null)flash.addFlashAttribute("docente", gt.getDocente().getNombreCompleto());
		if(gt.getRepresentante()!=null)flash.addFlashAttribute("representante", gt.getRepresentante().getNombreCompleto());
		
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
//############################################################### CREAR GRUPO DISCUSION #################################################################
		
	@PostMapping("/{id}/gd/crear")
	public String gdCrearG(@Valid @ModelAttribute("newDis")GrupoDis newDis, BindingResult result, 
							@PathVariable(name = "id")Long idMaImp, 
							Model model, RedirectAttributes flash) {
		
		MateriaImpartida maimp = materiaImpService.findById(idMaImp);
		model.addAttribute("newTeo", new GrupoTeo());
		model.addAttribute("newLab", new GrupoLab());
		model.addAttribute("maimpGes", maimp);
		model.addAttribute("tabActiva", "dis");
		if(result.hasErrors()) {
			model.addAttribute("errorCrearGrupo", "dis"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		if(grupoService.findDisByAsignatura(newDis.getNumeroGrupo(), maimp)!=null){
			model.addAttribute("otroError", "Ese numero de grupo ya existe");
			model.addAttribute("errorCrearGrupo", "dis"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		
		if(grupoService.crearGd(newDis, idMaImp)==null) flash.addFlashAttribute("error", "No se pudo crear grupo de Discusión");
		else flash.addFlashAttribute("success", "Grupo de Discusión creado exitosamente");
		
		flash.addFlashAttribute("tabActiva", "dis");
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
	
	@GetMapping("/gestionar/{id}/gd/{idg}")
	public String gdGestionar(@PathVariable(name = "id")Long idMaImp, @PathVariable(name = "idg")Long idGd, Model model, RedirectAttributes flash) {
		GrupoDis gd = grupoService.findDisById(idGd);
		ArrayList<EstudianteDeGrupo> alumnos = new ArrayList<EstudianteDeGrupo>();
		for(Matricula m:gd.getMatriculas()) {
			EstudianteDeGrupo e = new EstudianteDeGrupo();
			e.setCarnet(m.getEstudiante().getUsername());
			e.setNombre(m.getEstudiante().getNombreCompleto());
			e.setEstudianteActivo(m.getEstudiante().getActivo());
			if(m.getGrupoTeo()!=null) e.setGt(m.getGrupoTeo().getNumeroGrupo());
			if(m.getGrupoDis()!=null) e.setGd(m.getGrupoDis().getNumeroGrupo());
			if(m.getGrupoLab()!=null) e.setGl(m.getGrupoLab().getNumeroGrupo());
			alumnos.add(e);
		}
		flash.addFlashAttribute("tituloLista", "Grupo de Discusión "+gd.getNumeroGrupo());
		flash.addFlashAttribute("estudiantes", alumnos);
		flash.addFlashAttribute("tabActiva", "dis");
		flash.addFlashAttribute("gdid", gd.getId());
		flash.addFlashAttribute("grupo", gd);
		String msg = (String) model.getAttribute("myMsg");
		if(msg!=null)flash.addFlashAttribute("info", msg);
		if(gd.getDocente()!=null)flash.addFlashAttribute("docente", gd.getDocente().getNombreCompleto());
		
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
//############################################################### CREAR GRUPO LABORATORIO #################################################################
	
	@PostMapping("/{id}/gl/crear")
	public String glCrearG(@Valid @ModelAttribute("newLab")GrupoLab newLab, BindingResult result, 
							@PathVariable(name = "id")Long idMaImp, 
							Model model, RedirectAttributes flash) {
		
		MateriaImpartida maimp = materiaImpService.findById(idMaImp);
		model.addAttribute("newTeo", new GrupoTeo());
		model.addAttribute("newDis", new GrupoDis());
		model.addAttribute("maimpGes", maimp);
		model.addAttribute("tabActiva", "lab");
		if(result.hasErrors()) {
			model.addAttribute("errorCrearGrupo", "lab"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		
		if(grupoService.findLabByAsignatura(newLab.getNumeroGrupo(), maimp)!=null){
			model.addAttribute("otroError", "Ese número de grupo ya existe");
			model.addAttribute("errorCrearGrupo", "lab"); //variable que muestra el modal al cargar la página
			return "asignatura/gestionar";
		}
		
		if(grupoService.crearGl(newLab, idMaImp)==null) flash.addFlashAttribute("error", "No se pudo crear grupo de Laboratorio");
		else flash.addFlashAttribute("success", "Grupo de Laboratorio creado exitosamente");
		
		flash.addFlashAttribute("tabActiva", "lab");
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
	
	@GetMapping("/gestionar/{id}/gl/{idg}")
	public String glGestionar(@PathVariable(name = "id")Long idMaImp, @PathVariable(name = "idg")Long idGl, Model model, RedirectAttributes flash) {
		GrupoLab gl = grupoService.findLabById(idGl);
		
		ArrayList<EstudianteDeGrupo> alumnos = new ArrayList<EstudianteDeGrupo>();	//
		for(Matricula m:gl.getMatriculas()) {										//
			EstudianteDeGrupo e = new EstudianteDeGrupo();							//
			e.setCarnet(m.getEstudiante().getUsername());							// lista de estudiantes matriculados en el grupo
			e.setNombre(m.getEstudiante().getNombreCompleto());						//
			e.setEstudianteActivo(m.getEstudiante().getActivo());					//
			if(m.getGrupoTeo()!=null) e.setGt(m.getGrupoTeo().getNumeroGrupo());	//
			if(m.getGrupoDis()!=null) e.setGd(m.getGrupoDis().getNumeroGrupo());	//
			if(m.getGrupoLab()!=null) e.setGl(m.getGrupoLab().getNumeroGrupo());	//
			alumnos.add(e);															//
		}
		flash.addFlashAttribute("tituloLista", "Grupo de Laboratorio "+gl.getNumeroGrupo());
		flash.addFlashAttribute("estudiantes", alumnos);
		flash.addFlashAttribute("tabActiva", "lab");
		flash.addFlashAttribute("glid", gl.getId());
		flash.addFlashAttribute("grupo", gl);
		String msg = (String) model.getAttribute("myMsg");
		if(msg!=null)flash.addFlashAttribute("info", msg);
		if(gl.getDocente()!=null)flash.addFlashAttribute("docente", gl.getDocente().getNombreCompleto());
		System.out.println(msg);
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
//############################################################### CREAR EVALUACION #################################################################		
	@GetMapping("/{id}/eva/crear")
	public String evaCrearG(@PathVariable(name = "id")Long idMaImp, Model model) {
		
		model.addAttribute("eva", new EvaluacionEditar());
		model.addAttribute("maimpGes", materiaImpService.findById(idMaImp));
		model.addAttribute("tipoList", tipoEvaDao.findAll());
		return "asignatura/editar-evaluacion";
	}
	
	@PostMapping("/{idMaimp}/eva/crear")
	public String evaCrearP(@Valid @ModelAttribute("eva")EvaluacionEditar eva, BindingResult result, 
							@PathVariable(name = "idMaimp")Long idMaImp, 
							Model model, RedirectAttributes flash) {
		eva.setAsignatura(idMaImp);
		System.out.println(eva.getId());
		System.out.println(eva.getAsignatura());
		System.out.println(eva.getNumeroEvaluacion());
		System.out.println(eva.getTipo());
		System.out.println(eva.getActivo());
		System.out.println(eva.getFechaEvaluacion());
		if(result.hasErrors()) {
			
			
			for(ObjectError error: result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			System.out.println("en hasErrors");
			model.addAttribute("maimpGes", materiaImpService.findById(idMaImp));
			model.addAttribute("tipoList", tipoEvaDao.findAll());
			return "asignatura/editar-evaluacion";
		}
		if(evaluacionService.evaluacionYaExiste(eva)) {
			System.out.println("en ya existe");
			model.addAttribute("maimpGes", materiaImpService.findById(idMaImp));
			model.addAttribute("tipoList", tipoEvaDao.findAll());
			model.addAttribute("yaExisteError",	"Ya existe esta evaluación");
			return "asignatura/editar-evaluacion";
		}
		evaluacionService.guardar(eva);
		flash.addFlashAttribute("success", "Evaluación guardada con exito");
		flash.addFlashAttribute("tabActiva", "eva");
		return "redirect:/asignatura/gestionar/"+idMaImp;
	}
	
//############################################################### Editar EVALUACION #################################################################
	@GetMapping("/eva/{id}/editar")
	public String evaEditarG(@PathVariable(name = "id")Long evaId, Model model) {
		Evaluacion eva = evaluacionService.findById(evaId);
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		model.addAttribute("eva", eva);
		model.addAttribute("maimpGes", eva.getAsignatura());
		model.addAttribute("tipoList", tipoEvaDao.findAll());
		model.addAttribute("editar", "editar");
		
		return "asignatura/editar-evaluacion";
	}
	
//############################################################### Editar EVALUACION #################################################################
	@PostMapping("/{id}/importar/csv")
	public String importarCsvP(@RequestParam("file") MultipartFile file,@PathVariable(name = "id")Long maimpGes, Model model, RedirectAttributes flash) {
		if (file.isEmpty()) {
            flash.addFlashAttribute("error", "no se seleccionó ningún archivo");
        } else {
            // Convertir el archivo csv para crear una lista de objetos de tipo "UsuarioCsv"
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            	String DELIMITER = ",";              
                String line;
                ArrayList<UsuarioCsv> alumnos =new ArrayList<UsuarioCsv>();
                int i=0;
                while ((line = reader.readLine()) != null) {// leer el archivo linea por linea                    
                	UsuarioCsv alumno =new UsuarioCsv();
                    String[] columns = line.split(DELIMITER);// Guardar las columnas de la linea en un arreglo
                    int j=0;
                    if(i!=0) {
	                    for(String s:columns) {
	                    	
	                		switch (j) {
	                		case 0:
	                			if(!s.isBlank()) alumno.setNumero(s);break;
	                		case 1:
	                			if(!s.isBlank()) alumno.setpNombre(s);break;
	                		case 2:
	                			if(!s.isBlank()) alumno.setsNombre(s);break;
	                		case 3:
	                			if(!s.isBlank()) alumno.setpApellido(s);break;
	                		case 4:
	                			if(!s.isBlank()) alumno.setsApellido(s);break;
	                		case 5:
	                			if(!s.isBlank()) alumno.setCarnet(s.toUpperCase());break;
	                		case 6:
	                			if(!s.isBlank()) alumno.setGt(Integer.parseInt(s));break; 
	                		case 7:
	                			if(!s.isBlank()) alumno.setGd(Integer.parseInt(s));break;
	                		case 8:
	                			if(!s.isBlank()) alumno.setGl(Integer.parseInt(s));break;
	                		}
	                		//System.out.print(" "+j+"->"+s+" ");//BORRAR, para pruebas unicamente
	                		j++;
	                    }
	                    alumnos.add(alumno);
                    }
                    i++;
                    //System.out.println("");//BORRAR, para pruebas unicamente
                }
                
            for(UsuarioCsv u: alumnos) System.out.println(u.toString()); //BORRAR, para pruebas unicamente
            InfoMatriculaCsv info = matriculaService.matricularViaCsv(alumnos, maimpGes);
            System.out.println("----matriculados----");
            for(String msg:info.getMatriculados()) System.out.println(msg);
            System.out.println("----Actualizados----");
            for(String msg:info.getActualizados()) System.out.println(msg);
            System.out.println("----no Matriculados----");
            for(String msg:info.getNoMatriculados()) System.out.println(msg);
            System.out.println("----Registros nuevos----");
            for(String msg:info.getRegistrados()) System.out.println(msg);
            
            } catch (IOException ex) {
            	flash.addFlashAttribute("error", ex.getMessage());
            }
        }
		
		return "redirect:/asignatura/gestionar/"+maimpGes;
	}
//###############################   ACCEDER AL LISTADO PARA ASIGNAR DOCENTE A GRUPO ################################################
	@GetMapping("/{tipoGrupo}/{idgt}/docente")
	public String asignarDocente(@PathVariable(name = "idgt")Long idg, @PathVariable(name = "tipoGrupo")String tipoGrupo,Model model) {
		
		java.util.List<Usuario> listado = usuarioService.findAllDocentesActivos();
		model.addAttribute("listado", listado);
		switch (tipoGrupo) {
		case "gt":
			GrupoTeo gt = grupoService.findTeoById(idg);
			if(gt==null);
			model.addAttribute("tipoGrupo", "gt");
			if(gt.getDocente()!=null) model.addAttribute("actual", gt.getDocente().getNombreCompleto());
			model.addAttribute("titulo", "Asignar Docente a Grupo Teórico");
			model.addAttribute("numeroGrupo", gt.getNumeroGrupo());
			model.addAttribute("maimp", gt.getAsignatura());
			break;
		
		case "gd":
			GrupoDis gd = grupoService.findDisById(idg);
			if(gd==null);
			model.addAttribute("tipoGrupo", "gd");
			if(gd.getDocente()!=null) model.addAttribute("actual", gd.getDocente().getNombreCompleto());
			model.addAttribute("titulo", "Asignar Docente a Grupo de Discusión");
			model.addAttribute("numeroGrupo", gd.getNumeroGrupo());
			model.addAttribute("maimp", gd.getAsignatura());
			break;
		
		case "gl":
			GrupoLab gl = grupoService.findLabById(idg);
			if(gl==null);
			model.addAttribute("tipoGrupo", "gl");
			if(gl.getDocente()!=null) model.addAttribute("actual", gl.getDocente().getNombreCompleto());
			model.addAttribute("titulo", "Asignar Docente a Grupo de Laboratorio");
			model.addAttribute("numeroGrupo", gl.getNumeroGrupo());
			model.addAttribute("maimp", gl.getAsignatura());
			break;	
		}
		model.addAttribute("idGrupo", idg);
		model.addAttribute("docente", true);
		return "asignatura/asignar-docente";
	}
	
//###############################   ACCEDER AL LISTADO PARA ASIGNAR REPRESENTANTE A GRUPO TEORICO  ################################################
	@GetMapping("/gt/{idgt}/representante")
	public String asignarRepresentante(@PathVariable(name = "idgt")Long idg, Model model) {
		GrupoTeo gt = grupoService.findTeoById(idg);
		java.util.List<Usuario> listado = new ArrayList<Usuario>();
		for(Matricula m: gt.getAsignatura().getMatriculas() ) listado.add(m.getEstudiante());
		model.addAttribute("listado", listado);
		model.addAttribute("tipoGrupo", "gt");
		if(gt.getRepresentante()!=null) model.addAttribute("actual", gt.getRepresentante().getNombreCompleto());
		model.addAttribute("titulo", "Asignar Representante a Grupo Teórico");
		model.addAttribute("numeroGrupo", gt.getNumeroGrupo());
		model.addAttribute("maimp", gt.getAsignatura());
		//if(gt==null);
		
		model.addAttribute("idGrupo", idg);
		model.addAttribute("docente", false);
		return "asignatura/asignar-docente";
	}
	
//###############################    ASIGNAR DOCENTE A GRUPO ################################################
	@GetMapping("/asignarDocente/{idu}/{tipoGrupo}/{idg}")
	public String asignarDocente(@PathVariable(name = "idg")Long idg, @PathVariable(name = "idu")Long idu, @PathVariable(name = "tipoGrupo")String tipoGrupo,Model model,RedirectAttributes flash) {
		
		if(!grupoService.asignarAGrupo(idg, idu, tipoGrupo)) {
			flash.addFlashAttribute("error", "No se pudo asignar al usuario seleccionado");
			return "redirect:/asignatura/"+tipoGrupo+"/"+idg+"/representante";
		}
		Long mid=(long) 0;
		String msg = "";
		switch (tipoGrupo) {
		case "gt":
			GrupoTeo gt = grupoService.findTeoById(idg);
			mid = gt.getAsignatura().getId();
			msg = "Se asignó a '"+gt.getDocente().getNombreCompleto()+"' como docente encargado del grupo teórico "+gt.getNumeroGrupo();
			break;
		case "gd":
			GrupoDis gd = grupoService.findDisById(idg);
			mid = gd.getAsignatura().getId();
			msg = "Se asignó a '"+gd.getDocente().getNombreCompleto()+"' como docente encargado del grupo de discusión "+gd.getNumeroGrupo();
			break;
		case "gl":
			GrupoLab gl = grupoService.findLabById(idg);
			mid = gl.getAsignatura().getId();
			msg = "Se asignó a '"+gl.getDocente().getNombreCompleto()+"' como docente encargado del grupo teórico "+gl.getNumeroGrupo();
			break;
		}
		flash.addFlashAttribute("myMsg", msg);
		return "redirect:/asignatura/gestionar/"+mid+"/"+tipoGrupo+"/"+idg;
	}
	
	@GetMapping("/asignarRepresentante/{idu}/{tipoGrupo}/{idg}")
	public String asignarRepresentante(@PathVariable(name = "idg")Long idg, @PathVariable(name = "idu")Long idu, @PathVariable(name = "tipoGrupo")String tipoGrupo,Model model,RedirectAttributes flash) {
		
		if(!usuarioService.findById(idu).getEstudiante()) {
			flash.addFlashAttribute("error", "El usuario seleccionado no es estudiante");
			return "redirect:/asignatura/gt/"+idg+"/representante";
		}
		if(!grupoService.asignarAGrupo(idg, idu, tipoGrupo)) {
			flash.addFlashAttribute("error", "No se pudo asignar al usuario seleccionado");
			return "redirect:/asignatura/gt/"+idg+"/representante";
		}
		Long mid=(long) 0;
		GrupoTeo gt = grupoService.findTeoById(idg);
		mid = gt.getAsignatura().getId();
		String msg = "Se asignó a '"+gt.getRepresentante().getNombreCompleto()+"' como Representante del grupo teórico "+gt.getNumeroGrupo();
		
		flash.addFlashAttribute("myMsg", msg);
		return "redirect:/asignatura/gestionar/"+mid+"/"+tipoGrupo+"/"+idg;
	}
	
	@GetMapping("/solicitudes/rev/{ideva}")
	public String verSolicitudesRev(@PathVariable(name = "ideva")Long idEva, Model model) {
		Evaluacion eva = evaluacionService.findById(idEva);
		model.addAttribute("titulo", "Solicitudes de Revisión");
		model.addAttribute("eva", eva);
		return "asignatura/ver-solicitudes";
	}
	
	@GetMapping("/revisarRevision/{idrev}")
	public String verRevision(@PathVariable(name = "idrev")Long idRev, Model model,RedirectAttributes flash) {
		
		if(revisionService.findById(idRev).getEvaluacion().getAsignatura().getMateria().getDepartamento().getJefatura()==null) {
			flash.addFlashAttribute("error", "El departamento de la materia ("+
					revisionService.findById(idRev).getEvaluacion().getAsignatura().getMateria().getDepartamento().getNombre()
									+") actualmente no posee jefatura, imposible visualizar solicitud");
			return "redirect:/asignatura/solicitudes/rev/"+revisionService.findById(idRev).getEvaluacion().getId();
		}
		
		SolicitudRevision sol = revisionService.findById(idRev);
		model.addAttribute("titulo", "Solicitud de Revisión");
		model.addAttribute("sol", sol);
		return "asignatura/ver-revision";
	}
	
	@PostMapping("/revisarRevision")
	public String verRevisionP(@ModelAttribute(name = "sol")SolicitudRevision sol, Model model, RedirectAttributes flash) {
		System.out.println(sol.getMsg());
		if(sol.getMsg().isBlank() && sol.getAprobado() ) {
			model.addAttribute("titulo", "Solicitud de Revisión");
			model.addAttribute("sol", sol);
			model.addAttribute("otroError", "Este mensaje se mostrará al estudiante para informar el lugar, fecha y hora de la revisión, por favor no dejar en blanco");
			return "asignatura/ver-revision";
		}
		revisionService.save(sol);
		flash.addFlashAttribute("success", "Solicitud guardada con exito");
		return "redirect:/asignatura/solicitudes/rev/"+sol.getEvaluacion().getId();
	}
}









