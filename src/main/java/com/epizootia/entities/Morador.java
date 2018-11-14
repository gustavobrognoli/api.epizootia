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
@Table(name = "mod_epizootia_morador")
public class Morador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1600962819008427084L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Morador não  deve ser vazio")
	@Column(name = "nm_morador")
	private String morador;
	
	@NotEmpty(message = "Telefone não  deve ser vazio")
	@Column(name = "nu_telefone")
	private String telefone;

	public Morador() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getMorador() {
		return morador;
	}

	public void setMorador(String morador) {
		this.morador = morador;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Morador [id=" + id + ", morador=" + morador + ", telefone=" + telefone + "]";
	}

}
