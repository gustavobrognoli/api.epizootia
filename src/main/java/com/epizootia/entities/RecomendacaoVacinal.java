package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mod_epizootia_recomendacao_vacinal")
public class RecomendacaoVacinal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7271882072008186699L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Recomendacao Vacinal não  deve ser vazio")
	@Column(name = "ds_recomendacao_vacinal")
	private String recomendacaoVacinal;

	public RecomendacaoVacinal() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getRecomendacaoVacinal() {
		return recomendacaoVacinal;
	}

	public void setRecomendacaoVacinal(String recomendacaoVacinal) {
		this.recomendacaoVacinal = recomendacaoVacinal;
	}

}
