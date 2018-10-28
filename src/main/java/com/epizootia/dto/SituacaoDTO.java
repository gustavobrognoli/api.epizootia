package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class SituacaoDTO {

	private int id;
	private String situacao;

	public SituacaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Situacao não  deve ser vazia")
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "SituacaoDTO [id=" + id + ", situacao=" + situacao + "]";
	}

}
