package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_situacao")
public class Situacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174443649657795351L;

	private int id;
	private String situacao;

	public Situacao() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ds_situacao")
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Situacao [id=" + id + ", situacao=" + situacao + "]";
	}

}
