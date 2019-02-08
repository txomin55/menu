package com.txomin.dto;

import java.util.List;

public class PlatoDTO {

	private String nombre;
	private Integer kcal;
	private List<Integer> alergenos;
	private Integer idPlato;
	private String descripcion;
	
	public PlatoDTO(Integer idPlato, String nombre, Integer kcal, List<Integer> alergenos, String descripcion) {
		this.nombre = nombre;
		this.kcal = kcal;
		this.alergenos = alergenos;
		this.idPlato = idPlato;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getKcal() {
		return kcal;
	}
	
	public void setKcal(Integer kcal) {
		this.kcal = kcal;
	}
	
	public List<Integer> getAlergenos() {
		return alergenos;
	}
	
	public void setAlergenos(List<Integer> alergenos) {
		this.alergenos = alergenos;
	}
	
	public Integer getIdPlato() {
		return idPlato;
	}
	
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
