package com.Grupo19OO22021.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoPeriodo;
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
		return ViewRouteHelper.HOME;
	}
	
	@GetMapping("/irPrueba/{idPermiso}")
	public String prueba(@ModelAttribute("idPermiso") int idPermiso,Model model) {
		Permiso p = permisoService.findById2(idPermiso);
		
		PermisoPeriodo p1;
		//PermisoDiario p2;
		String url=null;
		
			if(p instanceof PermisoPeriodo) {
				p1 = (PermisoPeriodo) p;
				url = "https://franmsn2011.github.io/index.html?"
						+ "nombre="+p1.getPersona().getNombrePersona()+
						"&fecha="+p1.getFecha()+
						"&desde="+p1.getLugares().get(0).getNombreLugar()+
						"&hasta="+p1.getLugares().get(1).getNombreLugar();
						;
			}
			/*
			if(p instanceof PermisoDiario) {
				p2 = (PermisoDiario) p;
				url = "https://franmsn2011.github.io/index.html?"
						+ "nombre="+p2.getPersona().getNombrePersona()+
						"&fecha="+p2.getFecha()+
						"&desde="+p2.getLugares().get(0).getNombreLugar()+
						"&motivo="+p2.getMotivo();
						;
			}*/
		
		model.addAttribute("stringUrl", new StringUrl(url));
		
		return "prueba";
	}
	
		@GetMapping("/homeUsuario")
	public String mostrarHomeUsuario() {
		return ViewRouteHelper.HOMEU;
	}
	
	
}
