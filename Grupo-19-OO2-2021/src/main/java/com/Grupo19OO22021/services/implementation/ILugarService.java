package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Lugar;

public interface ILugarService {
	
    public List<Lugar> getAll();
	
	public Lugar findById(int id);
	

	public List<Lugar> getAllSolo();
	


}
