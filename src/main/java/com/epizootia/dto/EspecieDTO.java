package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class EspecieDTO {

	private int id;
	
	@NotEmpty(message = "Especie não deve ser vazia")
	private String especie;

	public EspecieDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "EspecieDTO [id=" + id + ", especie=" + especie + "]";
	}

}
