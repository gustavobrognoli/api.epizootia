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
@Table(name = "mod_epizootia_anormalidade")
public class Anormalidade implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3247695039962861171L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Anormalidade não deve ser vazia")
	@Column(name = "cd_sintoma")
	private String sintoma;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "anormalidade", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public String toString() {
		return "Anormalidade [id=" + id + ", sintoma=" + sintoma + ", animais=" + animais + "]";
	}

}