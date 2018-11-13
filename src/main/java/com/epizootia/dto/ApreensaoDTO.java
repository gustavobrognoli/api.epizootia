package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class ApreensaoDTO {

	private int id;

	@NotEmpty(message = "Apreensão não deve ser vazia")
	private String apreensao;

	public ApreensaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApreensao() {
		return apreensao;
	}

	public void setApreensao(String apreensao) {
		this.apreensao = apreensao;
	}

	@Override
	public String toString() {
		return "ApreensaoDTO [id=" + id + ", apreensao=" + apreensao + "]";
	}

}
