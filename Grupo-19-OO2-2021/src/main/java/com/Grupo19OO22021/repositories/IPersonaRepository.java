package com.Grupo19OO22021.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable>{
	public abstract Persona findByIdPersona(int idPersona);
	public abstract Persona findByNombrePersona(String name);

}
