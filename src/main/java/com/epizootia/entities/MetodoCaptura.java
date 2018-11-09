package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_mtd_captura")
public class MetodoCaptura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1709771024153585715L;

	private int id;
	private String metodoCaptura;

	public MetodoCaptura() {
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

	@Column(name = "ds_mtd_captura")
	public String getMetodoCaptura() {
		return metodoCaptura;
	}

	public void setMetodoCaptura(String metodoCaptura) {
		this.metodoCaptura = metodoCaptura;
	}

}
