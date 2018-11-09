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

	private int id;
	private boolean puca;
	private boolean castro;
	private boolean shanonn;
	private boolean cdc;

	public Equipamento() {
		// TODO Auto-generated constructor idadeAnimal
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
	
	@Column(name = "fg_equipamento_puca")
	public boolean isPuca() {
		return puca;
	}

	public void setPuca(boolean puca) {
		this.puca = puca;
	}

	@Column(name = "fg_equipamento_castro")
	public boolean isCastro() {
		return castro;
	}

	public void setCastro(boolean castro) {
		this.castro = castro;
	}

	@Column(name = "fg_equipamento_shanoon")
	public boolean isShanonn() {
		return shanonn;
	}

	public void setShanonn(boolean shanonn) {
		this.shanonn = shanonn;
	}

	@Column(name = "fg_equipamento_cdc")
	public boolean isCdc() {
		return cdc;
	}

	public void setCdc(boolean cdc) {
		this.cdc = cdc;
	}

	@Override
	public String toString() {
		return "Equipamento [id=" + id + ", puca=" + puca + ", castro=" + castro + ", shanonn=" + shanonn + ", cdc="
				+ cdc + "]";
	}

}
