package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import converters.UsuarioConverter;
import entities.Perfil;
import entities.Usuario;
import exception.UsuarioExistenteException;
import models.UsuarioModel;
import repositories.IPerfilRepository;
import repositories.IUsuarioRepository;
import services.implementation.IUsuarioService;


@Service("usuarioService")
public class UsuarioService implements IUsuarioService ,UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	
	@Override
	public List<Usuario> getAll() {
		List<Usuario> list = usuarioRepository.findAll();
		List<Usuario> l = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isActivo()) {
				l.add(list.get(i));
			}
		}
		return l;
	}
	@Override
	public List<Usuario> getAllPerfiles() {
		List<Usuario> list = usuarioRepository.findAll();
		List<Usuario> l = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Usuario usuario= list.get(i);
			usuario.setPerfil(perfilRepository.findByIdPerfil(usuario.getTipoUsuario()));
			l.add(usuario);
		}
		return l;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		entities.Usuario user = usuarioRepository.findByNombreUsuario(nombreUsuario);
		user.setPerfil(perfilRepository.findByIdPerfil(user.getPerfil().getIdPerfil()));
		return buildUser(user, buildGrantedAuthorities(user.getPerfil()));
	}
	private User buildUser(entities.Usuario user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getNombreUsuario(), user.getPassword(), user.isActivo(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Perfil perfil) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		
			grantedAuthorities.add(new SimpleGrantedAuthority(perfil.getNombrePerfil()));
		
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}

	@Override
	public UsuarioModel insertOrUpdate(UsuarioModel usuarioModel) {
		try {
			usuarioModel.setTipoUsuario(usuarioModel.getPerfil().getIdPerfil());
			usuarioModel.setActivo(true);
			Usuario usuario= usuarioRepository.save(usuarioConverter.modelToEntity(usuarioModel));
			return usuarioConverter.entityToModel(usuario);
		} catch (Exception e) {
			throw new UsuarioExistenteException(
					"No se puede agregar ese usuario porque ya hay un usuario con ese nombre");

		}
//		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public boolean remove(int id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean darDeBaja(int idUsuario) {
		try {
			Usuario u= usuarioRepository.findByIdUsuario(idUsuario);
			u.setActivo(false);
			insertOrUpdate(usuarioConverter.entityToModel(u));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public UsuarioModel findById(int id) {
		return usuarioConverter.entityToModel(usuarioRepository.findByIdUsuario(id));
	}

	@Override
	public UsuarioModel findByNombreUsuario(String name) {
		UsuarioModel u= null;
		if(usuarioRepository.findByNombreUsuario(name)!=null){
			u=usuarioConverter.entityToModel(usuarioRepository.findByNombreUsuario(name));
		}
		return u;
	}
	
	@Override
	public List<UsuarioModel> findByDegreeNombre(String degreeName) {
		List<UsuarioModel> models = new ArrayList<UsuarioModel>();
		
		return models;
	}
	@Override
	public boolean validoPassword(UsuarioModel usuario,String password) {
		return !usuario.getPassword().equalsIgnoreCase(password);
	}
}