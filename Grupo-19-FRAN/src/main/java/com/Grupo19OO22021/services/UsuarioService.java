package com.Grupo19OO22021.services;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Grupo19OO22021.converters.UsuarioConverter;
import com.Grupo19OO22021.entities.Perfil;
import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.exception.UsuarioExistenteException;
import com.Grupo19OO22021.models.UsuarioModel;
import com.Grupo19OO22021.repositories.IPerfilRepository;
import com.Grupo19OO22021.repositories.IUsuarioRepository;
import com.Grupo19OO22021.services.implementation.IUsuarioService;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService, UserDetailsService {

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
			if (list.get(i).isActivo()) {
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
			Usuario usuario = list.get(i);
			usuario.setPerfil(perfilRepository.findByIdPerfil(usuario.getTipoUsuario()));
			l.add(usuario);
		}
		return l;
	}

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		com.Grupo19OO22021.entities.Usuario user = usuarioRepository.findByNombreUsuario(nombreUsuario);
		user.setPerfil(perfilRepository.findByIdPerfil(user.getPerfil().getIdPerfil()));
		return buildUser(user, buildGrantedAuthorities(user.getPerfil()));
	}

	private User buildUser(com.Grupo19OO22021.entities.Usuario user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getNombreUsuario(), user.getPassword(), user.isActivo(), true, true, true, // accountNonExpired,
																										// credentialsNonExpired,
																										// accountNonLocked,
				grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Perfil perfil) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		grantedAuthorities.add(new SimpleGrantedAuthority(perfil.getNombrePerfil()));

		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UsuarioModel insertOrUpdate(UsuarioModel usuarioModel) {
		try {
			usuarioModel.setTipoUsuario(usuarioModel.getPerfil().getIdPerfil());
			usuarioModel.setActivo(true);

			//ANTES DE GUARDAR AL USUARIO ENCRIPTO SU CONTRASEÑA
			String encodePassword = bCryptPasswordEncoder.encode(usuarioModel.getPassword());
			usuarioModel.setPassword(encodePassword);

			Usuario usuario = usuarioRepository.save(usuarioConverter.modelToEntity(usuarioModel));
			return usuarioConverter.entityToModel(usuario);
		} catch (Exception e) {
			throw new UsuarioExistenteException("No se puede agregar ya que hay un usuario con ese nombre de Usuario");

		}
//		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public boolean remove(int id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean darDeBaja(int idUsuario) {
		try {
			Usuario u = usuarioRepository.findByIdUsuario(idUsuario);
			u.setActivo(false);
			insertOrUpdate(usuarioConverter.entityToModel(u));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UsuarioModel findById(int id) {
		return usuarioConverter.entityToModel(usuarioRepository.findByIdUsuario(id));
	}

	@Override
	public UsuarioModel findByNombreUsuario(String name) {
		UsuarioModel u = null;
		if (usuarioRepository.findByNombreUsuario(name) != null) {
			u = usuarioConverter.entityToModel(usuarioRepository.findByNombreUsuario(name));
		}
		return u;
	}

	@Override
	public List<UsuarioModel> findByDegreeNombre(String degreeName) {
		List<UsuarioModel> models = new ArrayList<UsuarioModel>();

		return models;
	}

	// VALIDACIONES

	@Autowired
	IUsuarioRepository repository;

	@Override
	public Iterable<Usuario> getAllUsuarios() {
		return repository.findAll();
	}

	/*
	 * private boolean checkUsernameAvailable(Usuario usuario) throws Exception {
	 * Optional<Usuario> userFound=
	 * repository.findByNombre(usuario.getNombreUsuario()); if
	 * (userFound.isPresent()) { throw new
	 * Exception("Nombre de Usuario no disponible"); } return true; }
	 */

	@Override
	public boolean validoPassword(UsuarioModel usuario, String password) {
		return !usuario.getPassword().equalsIgnoreCase(password);
	}

}