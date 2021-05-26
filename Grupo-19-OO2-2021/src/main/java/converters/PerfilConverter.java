package converters;

import org.springframework.stereotype.Component;

import entities.Perfil;
import models.PerfilModel;
@Component("perfilConverter")
public class PerfilConverter {
	public PerfilModel entityToModel(Perfil perfil) {
		return new PerfilModel(perfil.getIdPerfil(),perfil.getNombrePerfil());
	}

	public Perfil modelToEntity(PerfilModel perfil) {
		return new Perfil(perfil.getIdPerfil(),perfil.getNombrePerfil());
	}
}
