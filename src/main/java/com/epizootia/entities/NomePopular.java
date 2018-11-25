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
@Table(name = "mod_epizootia_nm_popular")
public class NomePopular implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011679044431776081L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Nome popular não  deve ser vazio")
	@Column(name = "ds_nome")
	private String nome;

	public NomePopular() {
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

/*	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}*/

	@Override
	public String toString() {
		return "NomePopular [id=" + id + ", nome=" + nome /*+ ", animais=" + animais*/ + "]";
	}

}
