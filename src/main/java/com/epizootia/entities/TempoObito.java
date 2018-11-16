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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mod_epizootia_tempo_obito")
public class TempoObito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622200158947070017L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ds_tempo_obito")
	private String tempoObito;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tempoObito", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;

	public TempoObito() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(String tempoObito) {
		this.tempoObito = tempoObito;
	}

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "TempoObito [id=" + id + ", tempoObito=" + tempoObito + ", animais=" + animais + "]";
	}

}
