package com.Grupo19OO22021.controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.models.PerfilModel;
import com.Grupo19OO22021.pdf.GeneratePDF;
import com.Grupo19OO22021.services.PerfilService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	@Autowired
	@Qualifier("perfilService")
	private PerfilService perfilService;

	

	//spring.jpa.hibernate.ddl-auto=update
/*
	@PostMapping("/seve")
	public String seve(@Validated Perfil u, Model model) {
		try {
			PerfilService.addJugador(u);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return "formPerfil";
		}
		return "redirect:/";
	}
	*/
	//@PreAuthorize("hasRole('auditor')")
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("perfil", new PerfilModel());
		model.addAttribute("segunda", false);
		return ViewRouteHelper.NEWPERFIL;
	}
	
	//@PreAuthorize("hasRole('auditor')")
	@PostMapping("/seve")
	public String create(@ModelAttribute("perfil") PerfilModel perfilModel,Model model) {
		try {
			perfilService.insertOrUpdate(perfilModel);
			
		} catch (Exception e) {
			return "redirect:/perfil/new";
		}
		model.addAttribute("confirmacion", "Operacion sobre el perfil exitosa");
		return ViewRouteHelper.NEWPERFIL;
	}
	@GetMapping("/home/{idPerfil}")
	public String homePerfil(@ModelAttribute("idPerfil") int idPerfil,Model model) {
		model.addAttribute("perfil", perfilService.findById(idPerfil));
		return "homePerfil";
	}
	
	@GetMapping("/list")
	public ModelAndView listAllJugador() {
		GeneratePDF document = new GeneratePDF(new PDDocument());
		try {
			PDDocument documento = document.generatePDCListPerfil(perfilService.getAll());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mav = new ModelAndView(ViewRouteHelper.LISTP);
		mav.addObject("perfiles", perfilService.getAll());
		return mav;
	}

	//@PreAuthorize("hasRole('auditor')")
	@GetMapping("/editar/{idPerfil}")
	public String editar(@ModelAttribute("idPerfil") int idPerfil, Model model) {
		PerfilModel perfil= perfilService.findById(idPerfil);
		model.addAttribute("perfil",perfil);
		return ViewRouteHelper.NEWPERFIL;
	}
	
	//@PreAuthorize("hasRole('auditor')")
	@GetMapping("/eliminar/{idPerfil}")
	public String delete(@ModelAttribute("idPerfil") int idPerfil, Model model) {
		perfilService.remove(idPerfil);
		return "redirect:/perfil/list";
	}
}
