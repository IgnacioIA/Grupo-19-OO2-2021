package com.Grupo19OO22021.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Grupo19OO22021.services.LugarService;


@RestController()
public class LugarControllerRest {
	@Autowired
	@Qualifier("lugarService")
	private LugarService lugarService;
	
	@RequestMapping(value = "/lug", method = RequestMethod.GET)
	public String listAlllugares() {
		JSONObject myObject = new JSONObject();
		myObject.put("lugares", lugarService.getAllSolo());
		return myObject.toString();
	}

}
