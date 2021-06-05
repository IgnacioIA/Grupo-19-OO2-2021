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

import com.Grupo19OO22021.models.UsuarioModel;
import com.Grupo19OO22021.services.UsuarioService;

@RestController()
public class UsuarioControllerRest {
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@RequestMapping(value = "/usuar", method = RequestMethod.GET)
	public String listAllperfiles() {
		JSONObject myObject = new JSONObject();
		myObject.put("usuarios", usuarioService.getAllActivoSinPerfiles());
		return myObject.toString();
	}

	@GetMapping("/traer/{idUsuario}")
	public String editar(@PathVariable int idPerfil, Model model) {
		UsuarioModel perfiles = usuarioService.findById(idPerfil);
		return perfiles.toString();
	}
}
