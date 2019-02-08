package com.txomin.dto;

import java.util.List;

public class SeccionDTO {

	private String nombre;
	private List<PlatoDTO> platos;

	public List<PlatoDTO> getPlatos() {
		return platos;
	}

	public void setPlatos(List<PlatoDTO> platos) {
		this.platos = platos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
