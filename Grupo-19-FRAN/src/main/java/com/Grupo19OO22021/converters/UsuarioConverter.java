package com.Grupo19OO22021.converters;

import org.springframework.stereotype.Component;

import com.Grupo19OO22021.entities.Usuario;
import com.Grupo19OO22021.models.UsuarioModel;

@Component("usuarioConverter")
public class UsuarioConverter {
	public UsuarioModel entityToModel(Usuario usuario) {
		return new UsuarioModel(usuario.getIdUsuario(),usuario.getNombreUsuario(),usuario.getNombre(),usuario.getApellido(),usuario.getTipoDocumento(),usuario.getNroDocumento(),usuario.getEmail(),usuario.getPassword(),usuario.getTipoUsuario(),usuario.isActivo(),usuario.getPerfil());
	}

	public Usuario modelToEntity(UsuarioModel usuario) {
		return new Usuario(usuario.getIdUsuario(),usuario.getNombreUsuario(),usuario.getNombre(),usuario.getApellido(),usuario.getTipoDocumento(),usuario.getNroDocumento(),usuario.getEmail(),usuario.getPassword(),usuario.getTipoUsuario(),usuario.isActivo(),usuario.getPerfil());
	}
}
