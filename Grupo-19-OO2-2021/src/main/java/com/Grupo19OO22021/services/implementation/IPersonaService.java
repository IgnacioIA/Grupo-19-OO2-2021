package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Persona;

public interface IPersonaService {
	
	public List<Persona> getAll();
	
	public Persona findById(int id);
	
	public Persona insertOrUpdate(Persona persona);

	public List<Persona> getAllPersonaSolo();

	public Persona findByNombrePersona(String name);
	
}
