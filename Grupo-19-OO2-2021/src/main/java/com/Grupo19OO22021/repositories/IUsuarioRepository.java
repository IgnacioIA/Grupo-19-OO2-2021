package com.Grupo19OO22021.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Usuario;

@Repository("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable>{

	public abstract Usuario findByIdUsuario(int id);
	
	public abstract Usuario findByNombreUsuario(String name);
	
	public abstract Optional<Usuario> findByNombre(String nombreUsuario);
	
	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil WHERE u.nombreUsuario= (:nombreUsuario)")
	public abstract Usuario findByNombreUsuarioAndFetchPerfilEagerly(@Param("nombreUsuario") String nombreUsuario);
	
	public abstract List<Usuario> findAllByActivo(boolean activo);
	
}
