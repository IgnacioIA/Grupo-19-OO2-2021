package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.models.RodadoModel;

public interface IRodadoService {
	
    public List<Rodado> getAll();
	
	public RodadoModel findById(int id);
	
	public RodadoModel findByDominio(String dominio);
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
	public boolean remove(int id);

	public List<Rodado> getAllSolo();
	

}
