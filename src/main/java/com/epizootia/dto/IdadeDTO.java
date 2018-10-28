package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class IdadeDTO {

	private int id;
	private String idade;

	public IdadeDTO() {
		// TODO Auto-generated constructor idadeAnimal
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Idade não deve ser vazia")
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "IdadeDTO [id=" + id + ", idade=" + idade + "]";
	}

}
