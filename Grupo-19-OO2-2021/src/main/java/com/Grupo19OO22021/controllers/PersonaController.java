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
		model.addAttribute("usuario", new Usuario());
		return ViewRouteHelper.ASIGNARUSUARIO;
	}

	@PostMapping("/asigna")
	public String asigna(@Valid @ModelAttribute("Usuario") Usuario usuario, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			String[] array = usuario.getNombreUsuario().split(",");
			UsuarioModel u = usuarioService.findByNombreUsuario(array[0]);
			Persona p = new Persona(0, u.getNombre(), u.getApellido(), u.getNroDocumento(), u.getNombreUsuario());
			model.addAttribute("persona", p);
			Set<Usuario> list = p.getUsuarios();
			list.add(usuarioConverter.modelToEntity(u));
			p.setUsuarios(list);
			model.addAttribute("tipo", "asd");
		}
		return ViewRouteHelper.NEWPERSONA;
	}

	@GetMapping("/new")
	public String create(@Valid @ModelAttribute("persona") String persona, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			model.addAttribute("persona", persona);
			model.addAttribute("segunda", false);
		}
		return ViewRouteHelper.NEWPERSONA;
	}

	@PostMapping("/save")
	public String create(@Valid @ModelAttribute("persona") Persona persona, BindingResult result, ModelMap model) {
		if (result.hasErrors()) { // SI OCURRE UN ERROR
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

			}
		}

		return ViewRouteHelper.NEWPERSONA;
	}
	
}
