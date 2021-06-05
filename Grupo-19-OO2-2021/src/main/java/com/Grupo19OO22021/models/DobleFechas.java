package com.Grupo19OO22021.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class DobleFechas {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFinal;
	
	public DobleFechas() {
		super();
	}
	public DobleFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
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
	
}
