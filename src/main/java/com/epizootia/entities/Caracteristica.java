package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_caracteristica")
public class Caracteristica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577865937001692333L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nm_caracteristica")
	private String caracteristica;

	public Caracteristica() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	@Override
	public String toString() {
		return "Caracteristica [id=" + id + ", caracteristica=" + caracteristica + "]";
	}

}