package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class CorposAguaDTO {

	private int id;
	
	@NotEmpty(message = "Corpo d`Água não deve ser vazio")
	private String nome;

	public CorposAguaDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CorposAguaDTO [id=" + id + ", nome=" + nome + "]";
	}

}