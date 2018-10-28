package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class SexoDTO {

	private int id;
	private String sexo;

	public SexoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Sexo não  deve ser vazio")
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "SexoDTO [id=" + id + ", sexo=" + sexo + "]";
	}

}
