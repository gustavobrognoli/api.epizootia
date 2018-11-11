package com.epizootia.dto;


import javax.validation.constraints.NotEmpty;

public class UnidadeConservacaoDTO {

	private int id;
	private String nome;

	public UnidadeConservacaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Unidade Conservacao deve ter nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String unidadeConservacao) {
		this.nome = unidadeConservacao;
	}

	@Override
	public String toString() {
		return "UnidadeConservacaoDTO [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
