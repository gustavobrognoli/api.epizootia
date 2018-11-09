package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_recomendacao_vacinal")
public class RecomendacaoVacinal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7271882072008186699L;

	private int id;
	private String recomendacaoVacinal;

	public RecomendacaoVacinal() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ds_recomendacao_vacinal")
	public String getRecomendacaoVacinal() {
		return recomendacaoVacinal;
	}

	public void setRecomendacaoVacinal(String recomendacaoVacinal) {
		this.recomendacaoVacinal = recomendacaoVacinal;
	}

}
