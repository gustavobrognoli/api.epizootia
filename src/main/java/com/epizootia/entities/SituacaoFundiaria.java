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
@Table(name = "mod_epizootia_sit_fundiaria")
public class SituacaoFundiaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707253211515908961L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Situacao Fundiária não  deve ser vazia")
	@Column(name = "ds_nome")
	private String nome;

	public SituacaoFundiaria() {
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
		return "SituacaoFundiaria [id=" + id + ", nome=" + nome + "]";
	}

}
