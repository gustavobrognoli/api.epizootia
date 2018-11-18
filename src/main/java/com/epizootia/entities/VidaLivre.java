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
@Table(name = "mod_epizootia_vida_livre")
public class VidaLivre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4996677706290834824L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Vida Livre não deve ser vazia")
	@Column(name = "ds_vida_livre")
	private String vidaLivre;

	public VidaLivre() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVidaLivre() {
		return vidaLivre;
	}

	public void setVidaLivre(String vidaLivre) {
		this.vidaLivre = vidaLivre;
	}

	@Override
	public String toString() {
		return "VidaLivre [id=" + id + ", vidaLivre=" + vidaLivre + "]";
	}

}
