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
@Table(name = "mod_epizootia_idade")
public class Idade implements Serializable {

	private static final long serialVersionUID = -8818286631914859949L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Idade não deve ser vazia")
	@Column(name = "ds_idade")
	private String idade;

/*	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idade", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;*/

	public Idade() {
		// TODO Auto-generated constructor idadeAnimal
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

/*	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}*/

	@Override
	public String toString() {
		return "Idade [id=" + id + ", idade=" + idade + /*", animais=" + animais +*/ "]";
	}

}