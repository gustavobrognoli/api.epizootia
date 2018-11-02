package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class MoradorDTO {

	private int id;
	private String morador;
	private String telefone;

	public MoradorDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Morador não  deve ser vazio")
	public String getMorador() {
		return morador;
	}

	public void setMorador(String morador) {
		this.morador = morador;
	}

	@NotEmpty(message = "Telefone não  deve ser vazio")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Morador [id=" + id + ", morador=" + morador + ", telefone=" + telefone + "]";
	}

	
	
}
