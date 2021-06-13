package com.Grupo19OO22021.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permisoperiodo")
public class PermisoPeriodo extends Permiso{
	
	@Column(name="cantdias")
	private int cantDias;
	
	@Column(name="vacaciones")
	private boolean vacaciones ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idRodado")
	private Rodado rodado;
	
	public PermisoPeriodo() {
	}


	public PermisoPeriodo(int idPermiso,Persona pedido, LocalDate fecha,int cantDias, boolean vacaciones, Rodado rodado) {
		super(idPermiso, pedido, fecha);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public int getCantDias() {
		return cantDias;
	}
	public Persona getPedido() {
		return super.getPersona();
	}
	

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}


	public boolean isVacaciones() {
		return vacaciones;
	}


	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}


	public Rodado getRodado() {
		return rodado;
	}


	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}

}
