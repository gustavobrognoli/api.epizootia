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
@Table(name = "mod_epizootia_cativeiro")
public class Cativeiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3788401594712161059L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Cativeiro não deve ser vazio")
	@Column(name = "ds_cativeiro")
	private String cativeiro;

	public Cativeiro() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCativeiro() {
		return cativeiro;
	}

	public void setCativeiro(String cativeiro) {
		this.cativeiro = cativeiro;
	}

	@Override
	public String toString() {
		return "Cativeiro [id=" + id + ", cativeiro=" + cativeiro + "]";
	}

}
