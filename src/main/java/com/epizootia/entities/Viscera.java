package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_visceras")
public class Viscera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195875444412713276L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fg_coleta")
	private Boolean coleta;

	@Column(name = "ds_viscera")
	private String viscera;

	@Column(name = "ds_motivo")
	private String motivo;

	public Viscera() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViscera() {
		return viscera;
	}

	public void setViscera(String viscera) {
		this.viscera = viscera;
	}

	public Boolean getColeta() {
		return coleta;
	}

	public void setColeta(Boolean coleta) {
		this.coleta = coleta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "Viscera [id=" + id + ", coleta=" + coleta + ", viscera=" + viscera + ", motivo=" + motivo + "]";
	}

}