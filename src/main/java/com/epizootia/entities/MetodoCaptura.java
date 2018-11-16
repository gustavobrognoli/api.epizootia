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
@Table(name = "mod_epizootia_mtd_captura")
public class MetodoCaptura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1709771024153585715L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Metodo de Captura não  deve ser vazio")
	@Column(name = "ds_mtd_captura")
	private String metodoCaptura;

	public MetodoCaptura() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetodoCaptura() {
		return metodoCaptura;
	}

	public void setMetodoCaptura(String metodoCaptura) {
		this.metodoCaptura = metodoCaptura;
	}

}
