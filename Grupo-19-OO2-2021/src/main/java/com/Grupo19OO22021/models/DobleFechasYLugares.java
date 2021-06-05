package com.Grupo19OO22021.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DobleFechasYLugares {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinal;
	private int desde;
	private int hasta;
	
	public DobleFechasYLugares() {
		super();
	}
	
	public DobleFechasYLugares(LocalDate fechaInicio, LocalDate fechaFinal, int desde, int hasta) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.desde = desde;
		this.hasta = hasta;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getDesde() {
		return desde;
	}

	public void setDesde(int desde) {
		this.desde = desde;
	}

	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}
	
	
}
