package com.Grupo19OO22021.repositories;

import com.Grupo19OO22021.entities.Rodado;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Serializable>{
	public abstract Rodado findByIdRodado(int id);
	public abstract Rodado findByDominio(String dominio);
    public abstract Rodado findByVehiculo(String vehiculo);
	

}
