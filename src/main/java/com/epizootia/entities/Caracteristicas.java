package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_caracteristicas")
public class Caracteristicas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577865937001692333L;

	private int id;
	private Boolean natural;
	private Boolean domicilio;
	private Boolean rural;
	private Boolean urbano;
	
	public Caracteristicas() {
		// TODO Auto-generated constructor stub
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

	@Column(name = "fg_caracteristicas_natural")
	public Boolean getNatural() {
		return natural;
	}

	public void setNatural(Boolean natural) {
		this.natural = natural;
	}

	@Column(name = "fg_caracteristicas_domicilio")
	public Boolean getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Boolean domicilio) {
		this.domicilio = domicilio;
	}

	@Column(name = "fg_caracteristicas_rural")
	public Boolean getRural() {
		return rural;
	}

	public void setRural(Boolean rural) {
		this.rural = rural;
	}

	@Column(name = "fg_caracteristicas_urbano")
	public Boolean getUrbano() {
		return urbano;
	}

	public void setUrbano(Boolean urbano) {
		this.urbano = urbano;
	}

	@Override
	public String toString() {
		return "Caracteristicas [id=" + id + ", natural=" + natural + ", domicilio=" + domicilio + ", rural=" + rural
				+ ", urbano=" + urbano + "]";
	}
	
}
