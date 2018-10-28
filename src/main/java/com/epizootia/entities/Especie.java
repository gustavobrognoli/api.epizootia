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
public class Especie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5703247103098292551L;

	private int cd_id;
	private String ds_especie;
	
	public Especie() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getCd_id() {
		return cd_id;
	}

	public void setCd_id(int cd_id) {
		this.cd_id = cd_id;
	}

	@Column(name = "ds_especie")
	public String getDs_especie() {
		return ds_especie;
	}

	public void setDs_especie(String ds_especie) {
		this.ds_especie = ds_especie;
	}

}

	
