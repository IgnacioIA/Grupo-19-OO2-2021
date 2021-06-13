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

import com.Grupo19OO22021.models.RodadoModel;
import com.Grupo19OO22021.services.RodadoService;
@RestController()
public class RodadoControllerRest {
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@RequestMapping(value = "/rodad", method = RequestMethod.GET)
	public String listAllrodados() {
		JSONObject myObject = new JSONObject();
		myObject.put("rodados", rodadoService.getAllSolo());
		return myObject.toString();
	}

	@GetMapping("/traerR/{idPersona}")
	public String editarR(@PathVariable int idPersona, Model model) {
		RodadoModel perfiles = rodadoService.findById(idPersona);
		return perfiles.toString();
	}
}
