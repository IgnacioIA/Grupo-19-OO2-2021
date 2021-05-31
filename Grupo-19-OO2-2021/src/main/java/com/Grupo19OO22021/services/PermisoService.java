package com.Grupo19OO22021.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.Grupo19OO22021.converters.PermisoConverter;
import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoPeriodo;
import com.Grupo19OO22021.exception.UsuarioExistenteException;
import com.Grupo19OO22021.models.PermisoModel;
import com.Grupo19OO22021.models.PermisoPeriodoModel;
import com.Grupo19OO22021.repositories.IPermisoRepository;
import com.Grupo19OO22021.services.implementation.IPermisoService;

@Service("permisoService")
public class PermisoService implements IPermisoService{
	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public List<Permiso> getAll() {
		return permisoRepository.findAll();
	}
	
	@Override
	public PermisoModel findById(int id) {
		return permisoConverter.entityToModel(permisoRepository.findByIdPermiso(id));
	}

	@Override
	public PermisoPeriodoModel insertOrUpdatePermisoPeriodo(PermisoPeriodoModel permisoPeriodoModel) {
		try {
			//usuarioModel.setTipoUsuario(usuarioModel.getPerfil().getIdPerfil());
			permisoPeriodoModel.setVacaciones(true);
		/*	
			Rodado rodado= new Rodado(7,"dominio","auto1");
			permisoPeriodoModel.setRodado(rodado);
			Persona persona = new Persona(12,"Abigail","Alegre",392767234);
			permisoPeriodoModel.setPedido(persona);
         */
			PermisoPeriodo permisoPeriodo = permisoRepository.save(permisoConverter.modelToEntity(permisoPeriodoModel));
			return permisoConverter.entityToModel(permisoPeriodo);
		
		} catch (Exception e) {
			throw new UsuarioExistenteException("No se puede agregar");

		}	

	}

}
