package com.Grupo19OO22021.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.Grupo19OO22021.converters.PermisoConverter;
import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoDiario;
import com.Grupo19OO22021.entities.PermisoPeriodo;
import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.exception.UsuarioExistenteException;
import com.Grupo19OO22021.models.DobleFechas;
import com.Grupo19OO22021.models.DobleFechasYLugares;
import com.Grupo19OO22021.models.PermisoModel;
import com.Grupo19OO22021.repositories.IPermisoRepository;
import com.Grupo19OO22021.services.implementation.IPermisoService;
import com.mysql.cj.Session;

import net.bytebuddy.asm.Advice.Local;

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
	public PermisoPeriodo insertOrUpdatePermisoPeriodo(PermisoPeriodo permisoPeriodo) {
		try {
			//usuarioModel.setTipoUsuario(usuarioModel.getPerfil().getIdPerfil());
			permisoPeriodo.setVacaciones(true);
		/*	
			Rodado rodado= new Rodado(7,"dominio","auto1");
			permisoPeriodoModel.setRodado(rodado);
			Persona persona = new Persona(12,"Abigail","Alegre",392767234);
			permisoPeriodoModel.setPedido(persona);
         */int idLugar=permisoPeriodo.getLugares().get(0).getIdLugar();
         	int idLugar2=permisoPeriodo.getLugares().get(1).getIdLugar();
			
			permisoPeriodo.setLugares(new ArrayList<Lugar>());
			PermisoPeriodo p= permisoRepository.save(permisoPeriodo);
			int idPermiso =p.getIdPermiso();
			permisoRepository.insertLugares(idLugar, idPermiso);
			permisoRepository.insertLugares(idLugar2, idPermiso);
			
			return p;
		
		} catch (Exception e) {
			throw new UsuarioExistenteException("No se puede agregar");

		}	

	}
	@Override
	public PermisoDiario insertOrUpdatePermisoDiario(PermisoDiario permisoDiario) {
		try {
			//usuarioModel.setTipoUsuario(usuarioModel.getPerfil().getIdPerfil());
			//permisoPeriodo.setVacaciones(true);
		/*	
			Rodado rodado= new Rodado(7,"dominio","auto1");
			permisoPeriodoModel.setRodado(rodado);
			Persona persona = new Persona(12,"Abigail","Alegre",392767234);
			permisoPeriodoModel.setPedido(persona);
         */

			//permisoDiario.getIdPermiso()permisoDiario.getLugares().get(0).getIdLugar()+"');";
			int idLugar=permisoDiario.getLugares().get(0).getIdLugar();
			int idLugar2=permisoDiario.getLugares().get(1).getIdLugar();
			
			permisoDiario.setLugares(new ArrayList<Lugar>());
			PermisoDiario p= permisoRepository.save(permisoDiario);
			int idPermiso =p.getIdPermiso();
			permisoRepository.insertLugares(idLugar, idPermiso);
			permisoRepository.insertLugares(idLugar2, idPermiso);
			
			return p;
		
		} catch (Exception e) {
			throw new UsuarioExistenteException("No se puede agregar");

		}	

	}
	public List<Permiso> findByPermisoPorPersona(int idPersona){
		List<Permiso> list =  permisoRepository.findAll();
		List<Permiso> l = new ArrayList<Permiso>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getPersona().getIdPersona()==idPersona) {
				l.add(list.get(i));
			}
		}
		return l;
	}
	
	@Override
	public List<Permiso> traerPermisosEntreFechas(DobleFechas dobleFecha){
		List<Permiso> list=permisoRepository.findAll();
		List<Permiso> l = new ArrayList<Permiso>();
		for (int i = 0; i < list.size(); i++) {
			LocalDate fecha = list.get(i).getFecha();
			if(fecha.isBefore(dobleFecha.getFechaFinal()) && fecha.isAfter(dobleFecha.getFechaInicio())) {
				l.add(list.get(i));
			}
		}
		return l;
	
	}
	public List<Permiso> traerPermisosEntreFechasXlugares(DobleFechasYLugares dobleFechasYLugares){
		DobleFechas dobleFechas = new DobleFechas(dobleFechasYLugares.getFechaInicio(), dobleFechasYLugares.getFechaFinal());
		List<Permiso> list=traerPermisosEntreFechas(dobleFechas);
		List<Permiso> l= new ArrayList<Permiso>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getLugares().get(0).getIdLugar()==dobleFechasYLugares.getDesde() && list.get(i).getLugares().get(1).getIdLugar()==dobleFechasYLugares.getHasta()) {
				l.add(list.get(i));
			}
			
		}
		return l;
	}
}
