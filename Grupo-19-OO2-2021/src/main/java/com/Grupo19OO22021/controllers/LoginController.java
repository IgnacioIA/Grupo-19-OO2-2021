package com.Grupo19OO22021.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grupo19OO22021.services.UsuarioService;



@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

}