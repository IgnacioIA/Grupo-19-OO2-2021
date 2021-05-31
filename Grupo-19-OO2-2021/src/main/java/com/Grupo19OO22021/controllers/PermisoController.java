package com.Grupo19OO22021.controllers;

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

import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.models.PermisoPeriodoModel;
import com.Grupo19OO22021.services.PermisoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;

	
	
	@GetMapping("/newPermisoPeriodo")
	public String create(Model model) {
		model.addAttribute("permisoPeriodo", new PermisoPeriodoModel());
		return ViewRouteHelper.NEWPERMISOPERIODO;
	}
	
	
	@PostMapping("/savePermisoPeriodo")
	public String create(@Valid @ModelAttribute("permisoPeriodo") PermisoPeriodoModel permisoPeriodoModel, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("permisoPeriodo", permisoPeriodoModel);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
			permisoService.insertOrUpdatePermisoPeriodo(permisoPeriodoModel);
			model.addAttribute("permisoPeriodo", new PermisoPeriodoModel());
			model.addAttribute("confirmacion", "Operacion sobre el Permiso exitosa");
		
		} catch (Exception e) {
			model.addAttribute("formErrorMessage", e.getMessage());
			model.addAttribute("permisoPeriodo", permisoPeriodoModel);
			
		}
		}
				
		return ViewRouteHelper.NEWPERMISOPERIODO;
	}
	
	
}
