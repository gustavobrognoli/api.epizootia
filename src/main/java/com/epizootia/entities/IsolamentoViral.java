package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_isolamento_viral")
public class IsolamentoViral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4877694064359190904L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ds_resultado")
	private String resultado;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_genero", referencedColumnName = "cd_id")
	private Genero genero;

	public IsolamentoViral() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "IsolamentoViral [id=" + id + ", resultado=" + resultado + ", genero=" + genero + "]";
	}

}
