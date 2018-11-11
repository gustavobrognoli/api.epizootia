package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_unidade_conservacao")
public class UnidadeConservacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130208186639088930L;

	private int id;
	private String nome;

	public UnidadeConservacao() {
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
		return "UnidadeConservacao [id=" + id + ", nome=" + nome + "]";
	}

}
