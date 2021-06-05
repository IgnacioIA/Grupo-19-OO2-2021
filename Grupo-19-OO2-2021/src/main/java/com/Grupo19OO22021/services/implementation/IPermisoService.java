package com.Grupo19OO22021.services.implementation;

import java.util.List;

import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoDiario;
import com.Grupo19OO22021.entities.PermisoPeriodo;
import com.Grupo19OO22021.models.DobleFechas;
import com.Grupo19OO22021.models.PermisoModel;

public interface IPermisoService {

	public List<Permiso> getAll();

	public PermisoModel findById(int id);

	public PermisoPeriodo insertOrUpdatePermisoPeriodo(PermisoPeriodo permisoPeriodo);

	public PermisoDiario insertOrUpdatePermisoDiario(PermisoDiario permisoDiario);

	//public List<Permiso> formAsignaDobleFechas(DobleFechas dobleFecha);

	public List<Permiso> traerPermisosEntreFechas(DobleFechas dobleFecha);


}
