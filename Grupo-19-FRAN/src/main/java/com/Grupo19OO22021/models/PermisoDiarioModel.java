package com.Grupo19OO22021.models;

import java.time.LocalDate;

import com.Grupo19OO22021.entities.Persona;

public class PermisoDiarioModel extends PermisoModel {

	private String motivo;

	
	
	public PermisoDiarioModel() {

	}

	public PermisoDiarioModel(Persona pedido, LocalDate fecha, String motivo) {
		super(pedido, fecha);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
