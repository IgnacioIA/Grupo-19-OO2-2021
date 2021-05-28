package com.Grupo19OO22021.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Perfil;
@Repository("perfilRepository")
public interface IPerfilRepository extends JpaRepository<Perfil, Serializable>{
	public abstract Perfil findByIdPerfil(int id);
	public abstract Perfil findByNombrePerfil(String name);
}
