package com.Grupo19OO22021.repositories;


import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grupo19OO22021.entities.Permiso;
import com.Grupo19OO22021.entities.PermisoPeriodo;


@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable>{

	public abstract PermisoPeriodo findByIdPermiso(int id);

}
