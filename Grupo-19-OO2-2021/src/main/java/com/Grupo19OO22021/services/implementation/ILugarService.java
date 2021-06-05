package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.models.RodadoModel;

public interface ILugarService {
	
    public List<Lugar> getAll();
	
	public Lugar findById(int id);
	
	//public Lu findByDominio(String dominio);
	
	//public RodadoModel findByVehiculo(String vehiculo);
	
	//public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
//	public boolean remove(int id);

	public List<Lugar> getAllSolo();
	
	//public List<RodadoModel> findByDegreeNombre(String degreeName);


}
