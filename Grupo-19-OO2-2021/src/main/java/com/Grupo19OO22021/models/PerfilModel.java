package com.Grupo19OO22021.models;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.Grupo19OO22021.entities.Usuario;

public class PerfilModel {
	private int idPerfil;
	
	@NotBlank
	@Size(min=3, max=15,message="No se cumplen las reglas del tamano (3-15)")
	private String nombrePerfil;
	
	private Set<Usuario> usuarios;

	public PerfilModel() {}


	public PerfilModel (int idPerfil, String nombrePerfil) {
		super();
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
	}


	public int getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}


	public String getNombrePerfil() {
		return nombrePerfil;
	}


	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	
	
}
