package com.Grupo19OO22021.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grupo19OO22021.converters.UsuarioConverter;
import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoDiario;
import com.Grupo19OO22021.entities.PermisoPeriodo;
import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.models.DobleFechas;
import com.Grupo19OO22021.models.DobleFechasYLugares;
import com.Grupo19OO22021.models.PermisoPeriodoModel;
import com.Grupo19OO22021.models.RodadoModel;
import com.Grupo19OO22021.services.LugarService;
import com.Grupo19OO22021.services.PermisoService;
import com.Grupo19OO22021.services.PersonaService;
import com.Grupo19OO22021.services.RodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	

	
	@GetMapping("/newPermisoPeriodo")
	public String create(Model model) {
		model.addAttribute("permisoPeriodo", new PermisoPeriodoModel());
		return ViewRouteHelper.NEWPERMISOPERIODO;
	}
	
	
	@GetMapping("/asignarPermisoPeriodo")
	public String asignarPermisoPeriodo(Model model) {
		model.addAttribute("persona",new Persona());
		return "permiso/formAsignaPersonaPermisoPeriodo";
	}
	
	
	@PostMapping("/asignaPP")
	public String asigna(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		//String[] array = persona.getNombrePersona().split(",");
		Persona u = personaService.findById(persona.getIdPersona());
		Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
		
		PermisoPeriodo p= new PermisoPeriodo(0,u2,LocalDate.now(),0,false,new Rodado(0, "dominio", "auto"));
		//Set<Usuario> list = p.getPersona().getUsuarios();
		//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
		
		//p.getPedido().setUsuarios(list);
		model.addAttribute("permisoPeriodo", p);
		return "permiso/formPermisoPeriodo";
	}
	
	
	
	@PostMapping("/savePermisoPeriodo/{idPersona}")
	public String create(@ModelAttribute("permisoPeriodo") PermisoPeriodo permisoPeriodo,@ModelAttribute("idPersona") int idPersona,Model model) {
		permisoPeriodo.setPersona(personaService.findById(idPersona));
		Lugar desde =  permisoPeriodo.getLugares().get(0);
		Lugar hasta =  permisoPeriodo.getLugares().get(1);
		List<Lugar> list = new ArrayList<Lugar>();
		list.add(desde);
		list.add(hasta);
		permisoPeriodo.setLugares(list);
		permisoService.insertOrUpdatePermisoPeriodo(permisoPeriodo);
	
		//PermisoPeriodo permisoPeriodo = 
		/*if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("permisoPeriodo", permisoPeriodo);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
			permisoService.insertOrUpdatePermisoPeriodo(permisoPeriodo);
			model.addAttribute("permisoPeriodo", new PermisoPeriodo());
			model.addAttribute("confirmacion", "Operacion sobre el Permiso exitosa");
		
		} catch (Exception e) {
			model.addAttribute("formErrorMessage", e.getMessage());
			model.addAttribute("permisoPeriodo", permisoPeriodo);
			
		}
		}
		*/		
		return "home";
	}
	/*
	public PermisoPeriodo cargarPP(PermisoPeriodo permisoPeriodo) {
		
		PermisoPeriodo p = new PermisoPeriodo()
	}
	*/
	//-------------------------------- PERMISO DIARIO
	@GetMapping("/newPermisoDiario")
	public String createpd(Model model) {
		model.addAttribute("permisoDiario", new PermisoDiario());
		return "permiso/formPermisoDiario";
	}
	
	
	@GetMapping("/asignarPermisoDiario")
	public String asignarPermisoDiario(Model model) {
		model.addAttribute("persona",new Persona());
		return "permiso/formAsignaPersonaPermisoDiario";
	}
	
	
	@PostMapping("/asignaPD")
	public String asignaPD(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		//String[] array = persona.getNombrePersona().split(",");
		Persona u = personaService.findById(persona.getIdPersona());
		Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
		//Persona u3=new  Persona();
		//u3.setIdPersona(persona.getIdPersona());
		PermisoDiario p= new PermisoDiario(0,u2,LocalDate.now(),"");
		//Set<Usuario> list = p.getPersona().getUsuarios();
		//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
		
		//p.getPedido().setUsuarios(list);
		model.addAttribute("permisoDiario", p);
		return "permiso/formPermisoDiario";
	}
	
	@PostMapping("/savePermisoDiario/{idPersona}")
	public String createPDiario(@ModelAttribute("permisoDiario") PermisoDiario permisoDiario,@ModelAttribute("idPersona") int idPersona,Model model) {
		permisoDiario.setPersona(personaService.findById(idPersona));
		Lugar desde =  permisoDiario.getLugares().get(0);
		Lugar hasta =  permisoDiario.getLugares().get(1);
		List<Lugar> list = new ArrayList<Lugar>();
		list.add(desde);
		list.add(hasta);
		permisoDiario.setLugares(list);
		permisoService.insertOrUpdatePermisoDiario(permisoDiario);
		return "home";
	}
	
	
	
	@PostMapping("/savePermisoDiario/{idPersona}/{idLugar}")
	public String createPD(@ModelAttribute("permisoDiario") PermisoDiario permisoDiario,@ModelAttribute("idPersona") int idPersona,Model model) {
		permisoDiario.setPersona(personaService.findById(idPersona));
		permisoService.insertOrUpdatePermisoDiario(permisoDiario);
	
		//PermisoPeriodo permisoPeriodo = 
		/*if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("permisoPeriodo", permisoPeriodo);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
			permisoService.insertOrUpdatePermisoPeriodo(permisoPeriodo);
			model.addAttribute("permisoPeriodo", new PermisoPeriodo());
			model.addAttribute("confirmacion", "Operacion sobre el Permiso exitosa");
		
		} catch (Exception e) {
			model.addAttribute("formErrorMessage", e.getMessage());
			model.addAttribute("permisoPeriodo", permisoPeriodo);
			
		}
		}
		*/		
		return "home";
	}
	
	//-----------------primera consulta
	//metodo 1 Permiso para auditor
	@Secured("ROLE_USER")
	@GetMapping("/asignarPersonaParaPermiso")
	public String asignarPermisoP(Model model) {
		model.addAttribute("persona",new Persona());
		return "permiso/formAsignaPersonaParaPermiso";
	}
	//metodo 2 Permiso es para todos
	@GetMapping("/asignarPersonaParaPermiso2")
	public String asignarPermisoP2(Model model) {
		model.addAttribute("persona",new Persona());
		return "permiso/formAsignaPersonaParaPermiso";
	}
	

	//@Secured("ROLE_USER")
	@PostMapping("/traerPermisosPorPersona")
	public String traerPermisoPorPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		//String[] array = persona.getNombrePersona().split(",");
		Persona u = personaService.findById(persona.getIdPersona());
		Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
		//Persona u3=new  Persona();
		//u3.setIdPersona(persona.getIdPersona());
		List<Permiso> p= permisoService.findByPermisoPorPersona(persona.getIdPersona());
		//Set<Usuario> list = p.getPersona().getUsuarios();
		//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
		
		//p.getPedido().setUsuarios(list);
		model.addAttribute("permisos", p);
		return "permiso/listPermisosPorPersona";
	}
	
	
	//-----------------Segunda consulta
		@Secured("ROLE_USER")
		@GetMapping("/asignarRodadoParaPermiso")
		public String asignarPermisoPR(Model model) {
			model.addAttribute("rodado",new Rodado());
			return "permiso/formAsignaRodadoParaPermiso";
		}
		
		//@Secured("ROLE_USER")
		@PostMapping("/traerPermisosPorRodado")
		public String traelkrPermisoPorRodado(@Valid @ModelAttribute("rodado") Rodado rodado, BindingResult result,ModelMap model) {
			//String[] array = persona.getNombrePersona().split(",");
			RodadoModel u = rodadoService.findById(rodado.getIdRodado());
			
			//Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
			//Persona u3=new  Persona();
			//u3.setIdPersona(persona.getIdPersona());
			Set<PermisoPeriodo> p= u.getPermisoPeriodos();
			//List<Permiso> p= permisoService.findByPermisoPorPersona(persona.getIdPersona());
			//Set<Usuario> list = p.getPersona().getUsuarios();
			//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
			
			//p.getPedido().setUsuarios(list);
			model.addAttribute("permisos", p);
			return "permiso/listPermisosPorRodado";
		}
	//----------------3 consulta
		@Secured("ROLE_USER")
		@GetMapping("/asignarDosFechasParaTraerPermiso")
		public String asignarFechas(Model model) {
			model.addAttribute("dobleFechas",new DobleFechas());
			return "permiso/formAsignaDobleFecha";
		}
		
		//@Secured("ROLE_USER")
		@PostMapping("/traerPermisosPorDobleFecha")
		public String traerPermisoPorDobleFecha(@Valid @ModelAttribute("dobleFechas") DobleFechas dobleFechas, BindingResult result,ModelMap model) {
			//String[] array = persona.getNombrePersona().split(",");
			//RodadoModel u = rodadoService.findById(rodado.getIdRodado());
			
			//Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
			//Persona u3=new  Persona();
			//u3.setIdPersona(persona.getIdPersona());
			//Set<PermisoPeriodo> p= u.getPermisoPeriodos();
			//List<Permiso> p= permisoService.findByPermisoPorPersona(persona.getIdPersona());
			//Set<Usuario> list = p.getPersona().getUsuarios();
			//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
			DobleFechas d= dobleFechas;
			List<Permiso> p = permisoService.traerPermisosEntreFechas(dobleFechas);
			//p.getPedido().setUsuarios(list);
			model.addAttribute("permisos", p);
			return "permiso/listPermisosPorFecha";
		}
		 //-----
		@Secured("ROLE_USER")
		@GetMapping("/traerPermisosPorFechaYLugar")
		public String asignarFechasXlugar(Model model) {
			model.addAttribute("dobleFechasPorLugares",new DobleFechasYLugares());
			return "permiso/formAsignaDobleFechaParaLugares";
		}
		
		//@Secured("ROLE_USER")
		@PostMapping("/traerPermisosPorDobleFechaPorLugares")
		public String traerPermisoPorDobleFechaXLugares(@Valid @ModelAttribute("dobleFechasPorLugares") DobleFechasYLugares dobleFechasPorLugares, BindingResult result,ModelMap model) {
			//String[] array = persona.getNombrePersona().split(",");
			//RodadoModel u = rodadoService.findById(rodado.getIdRodado());
			
			//Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
			//Persona u3=new  Persona();
			//u3.setIdPersona(persona.getIdPersona());
			//Set<PermisoPeriodo> p= u.getPermisoPeriodos();
			//List<Permiso> p= permisoService.findByPermisoPorPersona(persona.getIdPersona());
			//Set<Usuario> list = p.getPersona().getUsuarios();
			//list.add(usuarioConverter.modelToEntity(u.getUsuarios().get));
			DobleFechasYLugares d= dobleFechasPorLugares;
			List<Permiso> p = permisoService.traerPermisosEntreFechasXlugares(d);
			//p.getPedido().setUsuarios(list);
			model.addAttribute("permisos", p);
			return "permiso/listPermisosPorFechaXlugares";
		}
}
