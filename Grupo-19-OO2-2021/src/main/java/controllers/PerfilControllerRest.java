package controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.PerfilModel;
import services.PerfilService;

@RestController()
public class PerfilControllerRest {

	@Autowired
	@Qualifier("perfilService")
	private PerfilService perfilService;

	@RequestMapping(value = "/perfi", method = RequestMethod.GET)
	public String listAllperfiles() {
		JSONObject myObject = new JSONObject();
		myObject.put("perfiles", perfilService.getAllPerfilSolo());
		return myObject.toString();
	}

	@GetMapping("/traer/{idPerfil}")
	public String editar(@PathVariable int idPerfil, Model model) {
		PerfilModel perfiles = perfilService.findById(idPerfil);
		return perfiles.toString();
	}
}
