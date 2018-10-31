package com.epizootia.dto;

import javax.validation.constraints.Min;

public class UnidadeConservacaoDTO {

	private int id;
	private String unidadeConservacao;

	public UnidadeConservacaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Min(value = 0, message = "Unidade Conservacao não  deve ser vazia")
	public String getUnidadeConservacao() {
		return unidadeConservacao;
	}

	public void setUnidadeConservacao(String unidadeConservacao) {
		this.unidadeConservacao = unidadeConservacao;
	}

	@Override
	public String toString() {
		return "UnidadeConservacaoDTO [id=" + id + ", unidadeConservacao=" + unidadeConservacao + "]";
	}
	
	
}
