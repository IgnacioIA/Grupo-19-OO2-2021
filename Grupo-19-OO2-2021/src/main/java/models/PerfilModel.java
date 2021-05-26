package models;

import java.util.Set;

import entities.Usuario;

public class PerfilModel {
	private int idPerfil;
	
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
