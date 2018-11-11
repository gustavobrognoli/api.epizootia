package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_sit_fundiaria")
public class SituacaoFundiaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707253211515908961L;

	private int id;
	private String nome;

	public SituacaoFundiaria() {
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

	@Column(name = "ds_nome")
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
