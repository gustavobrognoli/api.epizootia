package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class NomePopularDTO {
	
	private int id;
	private String nome;
	
	public NomePopularDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome popular não  deve ser vazio")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "NomePopularDTO [id=" + id + ", nome=" + nome + "]";
	}
	
	
}