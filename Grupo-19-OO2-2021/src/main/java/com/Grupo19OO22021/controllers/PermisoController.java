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
		
		model.addAttribute("permisoPeriodo", p);
		return ViewRouteHelper.NEWPERMISOPERIODO;
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
	
		return ViewRouteHelper.HOME ;
	}
	
	
	
	@GetMapping("/newPermisoDiario")
	public String createpd(Model model) {
		model.addAttribute("permisoDiario", new PermisoDiario());
		return ViewRouteHelper.NEWPERMISODIARIO ;
	}
	
	
	@GetMapping("/asignarPermisoDiario")
	public String asignarPermisoDiario(Model model) {
		model.addAttribute("persona",new Persona());
		return ViewRouteHelper.ASIGNOPDIARIO;
	}
	
	
	@PostMapping("/asignaPD")
	public String asignaPD(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		//String[] array = persona.getNombrePersona().split(",");
		Persona u = personaService.findById(persona.getIdPersona());
		Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
		PermisoDiario p= new PermisoDiario(0,u2,LocalDate.now(),"");
		model.addAttribute("permisoDiario", p);
		return ViewRouteHelper.NEWPERMISODIARIO;
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
		return ViewRouteHelper.HOME;
	}
	
	
	
	@PostMapping("/savePermisoDiario/{idPersona}/{idLugar}")
	public String createPD(@ModelAttribute("permisoDiario") PermisoDiario permisoDiario,@ModelAttribute("idPersona") int idPersona,Model model) {
		permisoDiario.setPersona(personaService.findById(idPersona));
		permisoService.insertOrUpdatePermisoDiario(permisoDiario);
		
		return ViewRouteHelper.HOME;
	}
	
	
	
	@Secured("ROLE_USER")
	@GetMapping("/asignarPersonaParaPermiso")
	public String asignarPermisoP(Model model) {
		model.addAttribute("persona",new Persona());
		return ViewRouteHelper.ASIGNOPERSONAAPERMISO;
	}
	
	
	@GetMapping("/asignarPersonaParaPermiso2")
	public String asignarPermisoP2(Model model) {
		model.addAttribute("persona",new Persona());
		return ViewRouteHelper.ASIGNOPERSONAAPERMISO;
	}
	
	
	
	@PostMapping("/traerPermisosPorPersona")
	public String traerPermisoPorPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		
		Persona u = personaService.findById(persona.getIdPersona());
		Persona u2=new  Persona(u.getIdPersona(), u.getNombrePersona(), u.getApellidoPersona(), u.getDniPersona());
	
		List<Permiso> p= permisoService.findByPermisoPorPersona(persona.getIdPersona());
		
		model.addAttribute("permisos", p);
		return ViewRouteHelper.LISTPERMXPERS;
	}
	
	
		@Secured("ROLE_USER")
		@GetMapping("/asignarRodadoParaPermiso")
		public String asignarPermisoPR(Model model) {
			model.addAttribute("rodado",new Rodado());
			return ViewRouteHelper.ASIGNORODADOAPER;
		}
		
		@PostMapping("/traerPermisosPorRodado")
		public String traelkrPermisoPorRodado(@Valid @ModelAttribute("rodado") Rodado rodado, BindingResult result,ModelMap model) {

			RodadoModel u = rodadoService.findById(rodado.getIdRodado());
			
			Set<PermisoPeriodo> p= u.getPermisoPeriodos();
			
			model.addAttribute("permisos", p);
			return ViewRouteHelper.LISTPERMXRODADO;
		}
		
		
		
		@Secured("ROLE_USER")
		@GetMapping("/asignarDosFechasParaTraerPermiso")
		public String asignarFechas(Model model) {
			model.addAttribute("dobleFechas",new DobleFechas());
			return ViewRouteHelper.ASIGNORODOBLEFECHA;
		}
		
		
		
		@PostMapping("/traerPermisosPorDobleFecha")
		public String traerPermisoPorDobleFecha(@Valid @ModelAttribute("dobleFechas") DobleFechas dobleFechas, BindingResult result,ModelMap model) {
			
			DobleFechas d= dobleFechas;
			List<Permiso> p = permisoService.traerPermisosEntreFechas(dobleFechas);
		
			model.addAttribute("permisos", p);
			return ViewRouteHelper.LISTPERMXFECHA;
		}
		 //-----
		@Secured("ROLE_USER")
		@GetMapping("/traerPermisosPorFechaYLugar")
		public String asignarFechasXlugar(Model model) {
			model.addAttribute("dobleFechasPorLugares",new DobleFechasYLugares());
			return ViewRouteHelper.LISTPERMXFECHAXLUGAR;
		}
		
		
		@PostMapping("/traerPermisosPorDobleFechaPorLugares")
		public String traerPermisoPorDobleFechaXLugares(@Valid @ModelAttribute("dobleFechasPorLugares") DobleFechasYLugares dobleFechasPorLugares, BindingResult result,ModelMap model) {
			
			DobleFechasYLugares d= dobleFechasPorLugares;
			List<Permiso> p = permisoService.traerPermisosEntreFechasXlugares(d);
			//p.getPedido().setUsuarios(list);
			model.addAttribute("permisos", p);
			return ViewRouteHelper.LISTPERMXFECXLUGAR;
		}
}
