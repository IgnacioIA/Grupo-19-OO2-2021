package com.Grupo19OO22021.converters;

import org.springframework.stereotype.Component;

import com.Grupo19OO22021.entities.Rodado;
import com.Grupo19OO22021.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {

	public RodadoModel entityToModel(Rodado rodado) {
		return new RodadoModel(rodado.getIdRodado(),rodado.getDominio(),rodado.getVehiculo(),rodado.getPermisoPeriodos());
	}

	public Rodado modelToEntity(RodadoModel rodado) {
		return new Rodado(rodado.getIdRodado(),rodado.getDominio(),rodado.getVehiculo(),rodado.getPermisoPeriodos());
	}
}
