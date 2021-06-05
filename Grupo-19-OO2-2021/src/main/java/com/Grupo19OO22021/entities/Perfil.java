package com.Grupo19OO22021.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="perfil")
@Inheritance(strategy = InheritanceType.JOINED)
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPerfil;
	
	@Column(name="nombrePerfil")
	protected String nombrePerfil;
	
	@Column(name="createdat")
	@CreationTimestamp
	protected LocalDateTime createdAt;
	
	@Column(name="updatedat")
	@UpdateTimestamp
	protected LocalDateTime updatedAt;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="perfil")
	protected Set<Usuario> usuarios = new HashSet<Usuario>();

	

	public Perfil(int idPerfil, String nombrePerfil) {
		super();
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
	}


	public Perfil() {}


	public int getIdPerfil() {
		return idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}


	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}


	public Perfil(int idPerfil, String nombrePerfil, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	
}
