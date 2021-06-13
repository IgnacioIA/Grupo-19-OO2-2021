package com.Grupo19OO22021.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.services.PermisoService;

import usuarios.util.StringUrl;

@Controller
public class HomeController {
	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}
	
	@GetMapping("/irPrueba/{idPermiso}")
	public String prueba(@ModelAttribute("idPermiso") int idPermiso,Model model) {
		Permiso p = permisoService.findById2(idPermiso);
		List<Lugar> l = p.getLugares();
		String url = "https://franmsn2011.github.io/index.html?"
				+ "nombre="+p.getPersona().getNombrePersona()+
				"&fecha="+p.getFecha()+
				"&desde="+p.getLugares().get(0).getNombreLugar()+
				"&hasta="+p.getLugares().get(1).getNombreLugar();
				
				;
		model.addAttribute("stringUrl",new StringUrl(url));
		
		return "prueba";
	}
		@GetMapping("/homeUsuario")
	public String mostrarHomeUsuario() {
		return ViewRouteHelper.HOMEU;
	}
	
	
}
