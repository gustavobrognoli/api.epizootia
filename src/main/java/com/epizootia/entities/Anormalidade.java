package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_anormalidade")
public class Anormalidade implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -3247695039962861171L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cd_sintoma")
	private String sintoma;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public String toString() {
		return "Anormalidade [id=" + id + ", sintoma=" + sintoma + "]";
	}

}