package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_isolamento_viral")
public class IsolamentoViral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4877694064359190904L;

	private int id;
	private String isolamentoViral;
	private String resultado;
	private Boolean haemagogus;
	private Boolean sabethes;
	private Boolean aegypti;
	private Boolean anopheles;
	private Boolean albopictus;

	public IsolamentoViral() {
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

	@Column(name = "ds_isolamento_viral")
	public String getIsolamentoViral() {
		return isolamentoViral;
	}

	public void setIsolamentoViral(String isolamentoViral) {
		this.isolamentoViral = isolamentoViral;
	}

	@Column(name = "ds_resultado")
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Column(name = "fg_especies_encontradas_haemagogus")
	public Boolean getHaemagogus() {
		return haemagogus;
	}

	public void setHaemagogus(Boolean haemagogus) {
		this.haemagogus = haemagogus;
	}

	@Column(name = "fg_especies_encontradas_sabethes")
	public Boolean getSabethes() {
		return sabethes;
	}

	public void setSabethes(Boolean sabethes) {
		this.sabethes = sabethes;
	}

	@Column(name = "fg_especies_encontradas_aegypti")
	public Boolean getAegypti() {
		return aegypti;
	}

	public void setAegypti(Boolean aegypti) {
		this.aegypti = aegypti;
	}

	@Column(name = "fg_especie_encontrada_anopheles")
	public Boolean getAnopheles() {
		return anopheles;
	}

	public void setAnopheles(Boolean anopheles) {
		this.anopheles = anopheles;
	}

	@Column(name = "fg_especie_encontrada_albopictus")
	public Boolean getAlbopictus() {
		return albopictus;
	}

	public void setAlbopictus(Boolean albopictus) {
		this.albopictus = albopictus;
	}

	@Override
	public String toString() {
		return "IsolamentoViral [id=" + id + ", isolamentoViral=" + isolamentoViral + ", resultado=" + resultado
				+ ", haemagogus=" + haemagogus + ", sabethes=" + sabethes + ", aegypti=" + aegypti + ", anopheles="
				+ anopheles + ", albopictus=" + albopictus + "]";
	}

}
