package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_tempo_obito")
public class TempoObito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622200158947070017L;

	private int id;
	private String tempoObito;
	
	public TempoObito() {
		// TODO Auto-generated constructor stub
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
	
	@Column(name = "ds_tempo_obito")
	public String getTempoObito() {
		return tempoObito;
	}
	
	
	public void setTempoObito(String tempoObito) {
		this.tempoObito = tempoObito;
	}
	
	
	@Override
	public String toString() {
		return "TempoObito [id=" + id + ", tempoObito=" + tempoObito + "]";
	}
	
}
