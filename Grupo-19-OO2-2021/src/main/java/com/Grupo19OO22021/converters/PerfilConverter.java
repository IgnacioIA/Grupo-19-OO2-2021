package com.Grupo19OO22021.converters;

import org.springframework.stereotype.Component;

import com.Grupo19OO22021.entities.Perfil;
import com.Grupo19OO22021.models.PerfilModel;
@Component("perfilConverter")
public class PerfilConverter {
	public PerfilModel entityToModel(Perfil perfil) {
		return new PerfilModel(perfil.getIdPerfil(),perfil.getNombrePerfil());
	}

	public Perfil modelToEntity(PerfilModel perfil) {
		return new Perfil(perfil.getIdPerfil(),perfil.getNombrePerfil());
	}
}
