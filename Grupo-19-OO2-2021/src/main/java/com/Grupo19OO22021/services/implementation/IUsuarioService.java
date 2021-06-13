package com.Grupo19OO22021.services.implementation;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.models.UsuarioModel;

public interface IUsuarioService {
	
	public List<Usuario> getAll();

	public UsuarioModel findById(int id);

	public UsuarioModel findByNombreUsuario(String nombre);

	public UsuarioModel insertOrUpdate(UsuarioModel usuarioModel);

	public boolean remove(int id);

	public List<Usuario> getAllPerfiles();

	public List<UsuarioModel> findByDegreeNombre(String degreeName);

	public boolean validoPassword(UsuarioModel usuario, String password);

	public boolean darDeBaja(int idUsuario);

	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException;

	
	// USADOS PARA LA VALIDACION
	public Iterable<Usuario> getAllUsuarios();

	List<Usuario> getAllActivo();

	public UsuarioModel upDateBaja(UsuarioModel usuarioModel);
	
	public List<Usuario> getAllActivoSinPerfiles();
		

}
