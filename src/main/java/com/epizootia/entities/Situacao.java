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
@Table(name = "mod_epizootia_situacao")
public class Situacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174443649657795351L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Situacao não deve ser vazia")
	@Column(name = "ds_situacao")
	private String situacao;

/*	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "situacao", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;*/

	public Situacao() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

/*	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}*/

	@Override
	public String toString() {
		return "Situacao [id=" + id + ", situacao=" + situacao + /*", animais=" + animais + */"]";
	}

}
