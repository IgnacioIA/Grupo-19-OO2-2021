package com.Grupo19OO22021.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="permiso")
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPermiso;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="persona_id", nullable=false)
	private Persona persona;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha")
	private LocalDate fecha ;
	
	//EN LUGAR DE PONER DESDEHASTA LO LLAME ---> LUGARES
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "rel_permiso_lugar",
			joinColumns = @JoinColumn(name = "ID_PERMISO"),
			inverseJoinColumns = @JoinColumn(name="ID_LUGAR"))
	    private List<Lugar> lugares;
	
	
	public Permiso () {
		
	}


	public Permiso(int idPermiso, Persona persona, LocalDate fecha) {
		super();
		this.idPermiso = idPermiso;
		this.persona = persona;
		this.fecha = fecha;
	}


	public Permiso(int idPermiso, Persona persona, LocalDate fecha, List<Lugar> lugares) {
		super();
		this.idPermiso = idPermiso;
		this.persona = persona;
		this.fecha = fecha;
		this.lugares = lugares;
	}


	public int getIdPermiso() {
		return idPermiso;
	}


	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public List<Lugar> getLugares() {
		return lugares;
	}


	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
	}
	
	
}
