package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_sexo")
public class Sexo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6230838597293670449L;

	private int id;
	private String sexo;

	public Sexo() {
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

	@Column(name = "ds_sexo")
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Sexo [id=" + id + ", sexo=" + sexo + "]";
	}

}
