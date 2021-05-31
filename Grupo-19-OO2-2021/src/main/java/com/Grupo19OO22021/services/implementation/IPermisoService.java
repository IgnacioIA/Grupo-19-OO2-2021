package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.models.PermisoModel;
import com.Grupo19OO22021.models.PermisoPeriodoModel;

public interface IPermisoService {

	public List<Permiso> getAll();

	public PermisoModel findById(int id);

	public PermisoPeriodoModel insertOrUpdatePermisoPeriodo(PermisoPeriodoModel permisoPeriodoModel);


}
