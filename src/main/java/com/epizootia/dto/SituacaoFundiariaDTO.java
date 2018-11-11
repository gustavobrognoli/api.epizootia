package com.epizootia.dto;

public class SituacaoFundiariaDTO {

	private int id;
	private String nome;

	public SituacaoFundiariaDTO() {
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
		return "SituacaoFundiariaDTO [id=" + id + ", nome=" + nome + "]";
	}
	
}
