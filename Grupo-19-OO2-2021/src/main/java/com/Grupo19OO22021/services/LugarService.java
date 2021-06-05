package com.Grupo19OO22021.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.Grupo19OO22021.converters.RodadoConverter;
import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.models.RodadoModel;
import com.Grupo19OO22021.repositories.ILugarRepository;
import com.Grupo19OO22021.repositories.IRodadoRepository;
import com.Grupo19OO22021.services.implementation.ILugarService;
import com.Grupo19OO22021.services.implementation.IRodadoService;

@Service("lugarService")
public class LugarService implements ILugarService{
	

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	
	@Override
	public List<Lugar> getAll() {
		return lugarRepository.findAll();
	}
	@Override
	public List<Lugar> getAllSolo() {
		List<Lugar> list = lugarRepository.findAll();
		List<Lugar> l=new  ArrayList<Lugar>();
		for (int i = 0; i < list.size(); i++) {
			l.add(new Lugar(list.get(i).getIdLugar(),list.get(i).getNombreLugar(),list.get(i).getCodigoPostal()));
		}
		return l;
	}
	
	
	
	@Override
	public Lugar findById(int id) {
		return lugarRepository.findByIdLugar(id);
	}
/*
	@Override
	public RodadoModel findByDominio(String dominio) {
		RodadoModel u = null;
		if(rodadoRepository.findByDominio(dominio)!=null){
			u = rodadoConverter.entityToModel(rodadoRepository.findByDominio(dominio));
		}
		return u;
	}
	*/
	
}
