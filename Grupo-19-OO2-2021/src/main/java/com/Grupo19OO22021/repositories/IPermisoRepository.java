package com.Grupo19OO22021.repositories;


import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoPeriodo;


@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable>{

	public abstract PermisoPeriodo findByIdPermiso(int id);
	@Modifying
    @Transactional 
	@org.springframework.data.jpa.repository.Query(value= "INSERT INTO rel_permiso_lugar (id_lugar,id_permiso) VALUES (?1,?2)",nativeQuery = true)
	public abstract void insertLugares(int idLugar,@Param("idPermiso") int idPermiso);
}
