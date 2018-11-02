package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class RecomendacaoVacinalDTO {

	private int id;
	private String recomendacaoVacinal;

	public RecomendacaoVacinalDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Recomendacao Vacinal não  deve ser vazio")
	public String getRecomendacaoVacinal() {
		return recomendacaoVacinal;
	}

	public void setRecomendacaoVacinal(String recomendacaoVacinal) {
		this.recomendacaoVacinal = recomendacaoVacinal;
	}

	@Override
	public String toString() {
		return "RecomendacaoVacinalDTO [id=" + id + ", recomendacaoVacinal=" + recomendacaoVacinal + "]";
	}

}
