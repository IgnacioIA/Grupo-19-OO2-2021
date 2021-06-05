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
import com.Grupo19OO22021.models.RodadoModel;
import com.Grupo19OO22021.services.RodadoService;


@Controller
@RequestMapping("/rodado")
public class RodadoController {
	
	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("rodado", new RodadoModel());
		return ViewRouteHelper.NEWRODADO;
	}
	
	@PostMapping("/save")
	public String create(@Valid @ModelAttribute("rodado") RodadoModel rodadoModel, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("rodado", rodadoModel);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
			rodadoService.insertOrUpdate(rodadoModel);
			model.addAttribute("rodado", new RodadoModel());
			model.addAttribute("confirmacion", "Operacion sobre el Rodado exitosa");
		
		} catch (Exception e) {
			model.addAttribute("formErrorMessage", e.getMessage());
			model.addAttribute("rodado", rodadoModel);
			
		}
		}
				
		return ViewRouteHelper.NEWRODADO;
	}

	
	
	
}
