package com.epizootia.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nomePopular", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;

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

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "NomePopular [id=" + id + ", nome=" + nome + ", animais=" + animais + "]";
	}

}
