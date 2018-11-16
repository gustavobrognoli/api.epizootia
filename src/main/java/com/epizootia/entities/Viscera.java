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
@Table(name = "mod_epizootia_visceras")
public class Viscera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195875444412713276L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ds_viscera")
	private String viscera;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "viscera", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;

	public Viscera() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViscera() {
		return viscera;
	}

	public void setViscera(String viscera) {
		this.viscera = viscera;
	}

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "Viscera [id=" + id + ", viscera=" + viscera + ", animais=" + animais + "]";
	}

}