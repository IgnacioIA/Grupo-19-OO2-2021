package com.Grupo19OO22021.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.services.PersonaService;

@RestController()
public class PersonaControllerRest {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String listAllpersonas() {
		JSONObject myObject = new JSONObject();
		myObject.put("personas", personaService.getAllPersonaSolo());
		return myObject.toString();
	}

	@GetMapping("/traer/{idPersona}")
	public String editar(@PathVariable int idPerfil, Model model) {
		Persona perfiles = personaService.findById(idPerfil);
		return perfiles.toString();
	}
	
}
