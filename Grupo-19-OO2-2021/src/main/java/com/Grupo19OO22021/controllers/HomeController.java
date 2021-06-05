package com.Grupo19OO22021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Grupo19OO22021.helpers.ViewRouteHelper;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}
	
		@GetMapping("/homeUsuario")
	public String mostrarHomeUsuario() {
		return ViewRouteHelper.HOMEU;
	}
	
	
}
