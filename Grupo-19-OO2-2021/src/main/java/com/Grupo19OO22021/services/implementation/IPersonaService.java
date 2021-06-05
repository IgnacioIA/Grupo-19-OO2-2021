package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.models.PerfilModel;

public interface IPersonaService {
	public List<Persona> getAll();
	
	public Persona findById(int id);
	
	//public PerfilModel findByNombre(String nombre);
	
	public Persona insertOrUpdate(Persona persona);
	/*
	public boolean remove(int id);
	*/
	public List<Persona> getAllPersonaSolo();
	/*
	public List<PerfilModel> findByDegreeNombre(String degreeName);
*/

	public Persona findByNombrePersona(String name);
}
