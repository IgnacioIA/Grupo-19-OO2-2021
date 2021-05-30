package com.Grupo19OO22021.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.helpers.ViewRouteHelper;
import com.Grupo19OO22021.models.UsuarioModel;
import com.Grupo19OO22021.pdf.GeneratePDFUsuario;
import com.Grupo19OO22021.services.UsuarioService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.LOGOUT;
	}

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/usuario/index";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("usuario", new UsuarioModel());
		return ViewRouteHelper.NEWUSUARIO;
	}


	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String create(@Valid @ModelAttribute("usuario") UsuarioModel usuarioModel, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {   //SI OCURRE UN ERROR
			model.addAttribute("usuario", usuarioModel);
			model.addAttribute("confirmacion", "Operacion DENEGADA");
		} else {
			try {
				usuarioService.insertOrUpdate(usuarioModel);
				model.addAttribute("usuario", new UsuarioModel());
				model.addAttribute("confirmacion", "Operacion sobre el Usuario exitosa");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("usuario", usuarioModel);
				
			}

		} // cierra el else

		return ViewRouteHelper.NEWUSUARIO;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/home/{idUsuario}")
	public String homeUsuario(@ModelAttribute("idUsuario") int idUsuario, Model model) {
		model.addAttribute("usuario", usuarioService.findById(idUsuario));
		return ViewRouteHelper.HOMEU;
	}

	@GetMapping("/index")
	public String indexUsuario(/* @ModelAttribute("idUsuario") int idUsuario, */Model model) {
		// HashMap<String, String> has=
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass();
		String admin = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		String[] name = admin.substring(61, 80).split(",");
		UsuarioModel usuario = usuarioService.findByNombreUsuario(name[0]);
		// User usuario = (User)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// usuario.get
		model.addAttribute("usuario", usuario);
		return ViewRouteHelper.HOMEU;
	}

	@GetMapping("/list")
	public ModelAndView listAllUsuarios() {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.LISTU);
		mav.addObject("usuarios", usuarioService.getAll());
		return mav;
	}

	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{idUsuario}")
	public String editar(@ModelAttribute("idUsuario") int idUsuario, Model model) {
		UsuarioModel usuario = usuarioService.findById(idUsuario);
		model.addAttribute("usuario", usuario);
		return ViewRouteHelper.NEWUSUARIO;
	}

	
	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{idUsuario}")
	public String delete(@ModelAttribute("idUsuario") int idUsuario, Model model) {
		usuarioService.darDeBaja(idUsuario);
		return "redirect:/usuario/list";
	}
	
	
	
	@GetMapping("/usuarios.pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("aplication/pdf");
		
		
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		//String currentDateTime = dateFormatter.format(new Date(0, 0, 0));
		
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Usuarios.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<Usuario> listUsuario = usuarioService.getAll();
		
		
		GeneratePDFUsuario pdf =  new GeneratePDFUsuario(listUsuario);
		pdf.export(response);
		
		
		
	}
	
	
	
}
