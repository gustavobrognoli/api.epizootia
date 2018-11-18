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
@Table(name = "mod_epizootia_especie")
public class Especie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5703247103098292551L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Especie não deve ser vazia")
	@Column(name = "ds_especie")
	private String especie;

/*	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;*/
	
	public Especie() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

/*	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}
*/
	@Override
	public String toString() {
		return "Especie [id=" + id + ", especie=" + especie + /*", animais=" + animais + */"]";
	}

}
