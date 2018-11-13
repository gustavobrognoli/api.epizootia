package com.epizootia.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_ficha")
public class Ficha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511305471275217658L;

	private int id;
	private Calendar dataOcorrencia = Calendar.getInstance();
	
	/*private List <Animal> animais;*/
	
	private int quantidade;
	private Localidade localidade;
	private String municipio;
	
	private ClassificacaoFA ClassificacaoFA;
	
	
	public Ficha() {
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

	@Column(name = "dt_data_ocorrencia")
	public Calendar getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Calendar dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

/*	@OneToMany(mappedBy="ficha", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}*/

	@Column(name = "nu_quantidade")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "cd_id_localidade")
	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	@Column(name = "cd_municipio")
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "ds_classificacaoFA")
	public ClassificacaoFA getClassificacaoFA() {
		return ClassificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFA classificacaoFA) {
		ClassificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", dataOcorrencia=" + dataOcorrencia + /*", animais=" + animais +*/ ", quantidade="
				+ quantidade + ", localidade=" + localidade + ", municipio=" + municipio + ", ClassificacaoFA="
				+ ClassificacaoFA + "]";
	}

}
