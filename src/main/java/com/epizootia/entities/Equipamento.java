package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_equipamento")
public class Equipamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6404327884717730072L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ds_equipamento")
	private String equipamento;

	public Equipamento() {
		// TODO Auto-generated constructor idadeAnimal
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	public String toString() {
		return "Equipamento [id=" + id + ", equipamento=" + equipamento + "]";
	}
}
