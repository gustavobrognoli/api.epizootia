package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_especie")
public class Apreensao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 652329810544442867L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ds_apreensao")
	private String apreensao;

	public Apreensao() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApreensao() {
		return apreensao;
	}

	public void setApreensao(String apreensao) {
		this.apreensao = apreensao;
	}

	@Override
	public String toString() {
		return "Apreensao [id=" + id + ", apreensao=" + apreensao + "]";
	}
}
