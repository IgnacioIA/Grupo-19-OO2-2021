package com.Grupo19OO22021.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grupo19OO22021.converters.UsuarioConverter;
import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.models.UsuarioModel;
import com.Grupo19OO22021.services.PersonaService;
import com.Grupo19OO22021.services.UsuarioService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	

	
	@GetMapping("/asignarUsuario")
	public String asignarUsuario(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "persona/formAsignarUsuario";
	}
	@PostMapping("/asigna")
	public String asigna(@Valid @ModelAttribute("Usuario") Usuario usuario, BindingResult result,ModelMap model) {
		String[] array = usuario.getNombreUsuario().split(",");
		UsuarioModel u = usuarioService.findByNombreUsuario(array[0]);
		Persona p= new Persona(0, u.getNombre(), u.getApellido(), u.getNroDocumento());
		model.addAttribute("persona", p);
		Set<Usuario> list = p.getUsuarios();
		list.add(usuarioConverter.modelToEntity(u));
		p.setUsuarios(list);
		return "persona/formPersona";
	}
	@GetMapping("/new")
	public String create( @ModelAttribute("persona") String persona,Model model) {
		model.addAttribute("persona", persona);
		model.addAttribute("segunda", false);
		return "persona/formPersona";
	}
	
	@PostMapping("/seve")
	public String create(@Valid @ModelAttribute("persona") Persona persona, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("persona", persona);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
			personaService.insertOrUpdate(persona);
			model.addAttribute("persona", new Persona());
			model.addAttribute("confirmacion", "Operacion sobre el Perfil exitosa");
		
		} catch (Exception e) {
			model.addAttribute("formErrorMessage", e.getMessage());
			model.addAttribute("persona", persona);
			
			//return "redirect:/perfil/new";
		}
		}
				
		return ViewRouteHelper.NEWPERFIL;
	}
	/*
	@GetMapping("/home/{idPerfil}")
	public String homePerfil(@ModelAttribute("idPerfil") int idPerfil,Model model) {
		model.addAttribute("perfil", perfilService.findById(idPerfil));
		return "homePerfil";
	}
	
	@GetMapping("/list")
	public ModelAndView listAllJugador() {
		//GeneratePDF document = new GeneratePDF(new PDDocument());
		try {
			//PDDocument documento = document.generatePDCListPerfil(perfilService.getAll());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mav = new ModelAndView(ViewRouteHelper.LISTP);
		mav.addObject("perfiles", perfilService.getAll());
		return mav;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{idPerfil}")
	public String editar(@ModelAttribute("idPerfil") int idPerfil, Model model) {
		PerfilModel perfil= perfilService.findById(idPerfil);
		model.addAttribute("perfil",perfil);
		return ViewRouteHelper.NEWPERFIL;
	}
	*/
	
}