package com.Grupo19OO22021.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Lugar;
import com.Grupo19OO22021.entities.Usuario;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Serializable>{

	public abstract Lugar findByIdLugar(int id);
	
	//public abstract Lugar findByNombreUsuario(String name);
	
	//public abstract Optional<Usuario> findByNombre(String nombreUsuario);
	
	//@Query("SELECT u FROM Usuario u JOIN FETCH u.perfil WHERE u.nombreUsuario= (:nombreUsuario)")
	//public abstract Usuario findByNombreUsuarioAndFetchPerfilEagerly(@Param("nombreUsuario") String nombreUsuario);
	/*@Query("SELECT u FROM Usuario u JOIN fetch Perfil p on u.idPerfil =  p.idPerfil WHERE u.nombreUsuario= (:nombreUsuario)")
	public abstract Usuario findByNombreUsuarioAndFetchPerfilEagerly(@Param("nombreUsuario") String nombreUsuario);
*/
	//public abstract List<Lugar> findAllByActivo(boolean activo);
	
}
