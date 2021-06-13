package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Perfil;
import com.Grupo19OO22021.models.PerfilModel;

public interface IPerfilService  {
	
	public List<Perfil> getAll();
	
	public PerfilModel findById(int id);
	
	public PerfilModel findByNombre(String nombre);
	
	public PerfilModel insertOrUpdate(PerfilModel perfilModel);
	
	public boolean remove(int id);
	
	public List<Perfil> getAllPerfilSolo();
	
	public List<PerfilModel> findByDegreeNombre(String degreeName);
}
