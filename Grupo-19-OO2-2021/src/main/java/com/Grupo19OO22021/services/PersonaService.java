package com.Grupo19OO22021.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.repositories.IPersonaRepository;
import com.Grupo19OO22021.services.implementation.IPersonaService;
@Service("personaService")
public class PersonaService implements IPersonaService{
	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;
	
	
	@Override
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}
	@Override
	public List<Persona> getAllPersonaSolo() {
		List<Persona> list = personaRepository.findAll();
		List<Persona> l = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Persona p= list.get(i);
			l.add(new Persona(p.getIdPersona(),p.getNombrePersona() ,p.getApellidoPersona(),p.getDniPersona()));
		}
		return l;
	}


	@Override
	public Persona insertOrUpdate(Persona persona) {
		Persona p= personaRepository.save(persona);
			return p;
	}

	@Override
	public Persona findById(int id) {
		return personaRepository.findByIdPersona(id);
	}

	@Override
	public Persona findByNombrePersona(String name) {
		Persona u= null;
		if(personaRepository.findByNombrePersona(name)!=null){
			u=personaRepository.findByNombrePersona(name);
		}
		return u;
	}
	
}
