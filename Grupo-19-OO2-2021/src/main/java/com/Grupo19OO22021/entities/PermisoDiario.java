package com.Grupo19OO22021.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="permisodiario")
//@PrimaryKeyJoinColumn(name="idPermiso")
public class PermisoDiario extends Permiso {
	
	@Column(name="motivo")
	private String motivo;
	
	public PermisoDiario() {
		
	}
	public PermisoDiario(int idPermiso, Persona persona, LocalDate fecha, List<Lugar> lugares, String motivo) {
		super(idPermiso, persona, fecha, lugares);
		this.motivo = motivo;
	}
	public PermisoDiario(int idPermiso,Persona pedido, LocalDate fecha,String motivo) {
		super(idPermiso,pedido, fecha);
		this.motivo = motivo;
	}
	
	public Persona getPedido() {
		return super.getPersona();
	}
	
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	
	
	
}
