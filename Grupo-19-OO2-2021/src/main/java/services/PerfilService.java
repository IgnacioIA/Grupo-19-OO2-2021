package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import converters.PerfilConverter;
import entities.Perfil;
//import objetos2.demo.exception.PerfilExistenteException;
import models.PerfilModel;
import repositories.IPerfilRepository;
import services.implementation.IPerfilService;

@Service("perfilService")
public class PerfilService implements IPerfilService {

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("perfilConverter")
	private PerfilConverter perfilConverter;
	
	@Override
	public List<Perfil> getAll() {
		return perfilRepository.findAll();
	}
	@Override
	public List<Perfil> getAllPerfilSolo() {
		List<Perfil> list = perfilRepository.findAll();
		List<Perfil> l = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			l.add(new Perfil(list.get(i).getIdPerfil(), list.get(i).getNombrePerfil()));
		}
		return l;
	}


	@Override
	public PerfilModel insertOrUpdate(PerfilModel perfilModel) {
		//try {
			Perfil perfil= perfilRepository.save(perfilConverter.modelToEntity(perfilModel));
			return perfilConverter.entityToModel(perfil);
		/*} catch (Exception e) {
			throw new PerfilExistenteException(
					"No se puede agregar ese perfil porque ya hay un perfil con ese nombre");

		}*/
//		return perfilConverter.entityToModel(perfil);
	}

	@Override
	public boolean remove(int id) {
		try {
			perfilRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public PerfilModel findById(int id) {
		return perfilConverter.entityToModel(perfilRepository.findByIdPerfil(id));
	}

	@Override
	public PerfilModel findByNombre(String name) {
		PerfilModel u= null;
		if(perfilRepository.findByNombrePerfil(name)!=null){
			u=perfilConverter.entityToModel(perfilRepository.findByNombrePerfil(name));
		}
		return u;
	}
	
	@Override
	public List<PerfilModel> findByDegreeNombre(String degreeName) {
		List<PerfilModel> models = new ArrayList<PerfilModel>();
		
		return models;
	}
	
	
}
