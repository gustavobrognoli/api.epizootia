package com.epizootia.dto;

import javax.validation.constraints.NotNull;

public class ClassificacaoFADTO {

	private int id;
	private String classificacao;

	public ClassificacaoFADTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message = "Classificação não  deve ser vazia")
	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	@Override
	public String toString() {
		return "ClassificacaoFADTO [id=" + id + ", classificacao=" + classificacao + "]";
	}

}
