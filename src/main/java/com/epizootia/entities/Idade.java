package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_idade")
public class Idade implements Serializable {

	private static final long serialVersionUID = -8818286631914859949L;

	private int id;
	private String idade;

	public Idade() {
		// TODO Auto-generated constructor idadeAnimal
	}

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ds_idade")
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Idade [id=" + id + ", idade=" + idade + "]";
	}

}