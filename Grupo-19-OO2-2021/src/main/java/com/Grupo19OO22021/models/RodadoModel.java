package com.Grupo19OO22021.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.Grupo19OO22021.entities.PermisoPeriodo;

public class RodadoModel {
	

	private int idRodado;
	
	@NotBlank
	@Size(min=3, max=15,message="No se cumplen las reglas del tamano (3-15)")
	private String dominio;
	
	@NotBlank
	@Size(min=3, max=15,message="No se cumplen las reglas del tamano (3-15)")
	private String vehiculo;

	
	private Set<PermisoPeriodo> permisoPeriodos = new HashSet<PermisoPeriodo>();
	
	
	public RodadoModel() {
	}

	public RodadoModel(int idRodado,String dominio, String vehiculo) {
		super();
		this.idRodado=idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public int getIdRodado() {
		return idRodado;
	}

	public void setIdRodado(int idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Set<PermisoPeriodo> getPermisoPeriodos() {
		return permisoPeriodos;
	}

	public void setPermisoPeriodos(Set<PermisoPeriodo> permisoPeriodos) {
		this.permisoPeriodos = permisoPeriodos;
	}

	
	
	

}
