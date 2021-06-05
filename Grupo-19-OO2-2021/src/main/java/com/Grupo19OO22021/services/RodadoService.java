package com.Grupo19OO22021.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.Grupo19OO22021.converters.RodadoConverter;
import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.models.RodadoModel;
import com.Grupo19OO22021.repositories.IRodadoRepository;
import com.Grupo19OO22021.services.implementation.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService{
	

	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	
	@Override
	public List<Rodado> getAll() {
		return rodadoRepository.findAll();
	}
	@Override
	public List<Rodado> getAllSolo() {
		List<Rodado> list = rodadoRepository.findAll();
		List<Rodado> l=new  ArrayList<Rodado>();
		for (int i = 0; i < list.size(); i++) {
			l.add(new Rodado(list.get(i).getIdRodado(),list.get(i).getDominio(),list.get(i).getVehiculo()));
		}
		return l;
	}
	
	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		
			Rodado rodado= rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
			return rodadoConverter.entityToModel(rodado);
	}
	
	
	@Override
	public boolean remove(int id) {
		try {
			rodadoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	
	@Override
	public RodadoModel findById(int id) {
		return rodadoConverter.entityToModel(rodadoRepository.findByIdRodado(id));
	}

	@Override
	public RodadoModel findByDominio(String dominio) {
		RodadoModel u = null;
		if(rodadoRepository.findByDominio(dominio)!=null){
			u = rodadoConverter.entityToModel(rodadoRepository.findByDominio(dominio));
		}
		return u;
	}
	
	
}
