package com.Grupo19OO22021.converters;



import org.springframework.stereotype.Component;

import com.Grupo19OO22021.entities.PermisoPeriodo;
import com.Grupo19OO22021.models.PermisoPeriodoModel;


@Component("permisoConverter")
public class PermisoConverter {
	
	public PermisoPeriodoModel entityToModel(PermisoPeriodo permisoPeriodo) {
		return new PermisoPeriodoModel(permisoPeriodo.getIdPermiso(),permisoPeriodo.getPersona(),permisoPeriodo.getFecha(),permisoPeriodo.getCantDias(),permisoPeriodo.isVacaciones(),permisoPeriodo.getRodado());
	}

	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoPeriodo) {
		return new PermisoPeriodo(permisoPeriodo.getIdPermiso(),permisoPeriodo.getPedido(),permisoPeriodo.getFecha(),permisoPeriodo.getCantDias(),permisoPeriodo.isVacaciones(),permisoPeriodo.getRodado());
	}
	

}
