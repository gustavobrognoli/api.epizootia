package com.epizootia.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mod_epizootia_ficha")
public class Ficha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511305471275217658L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "dt_data_ocorrencia")
	private Calendar dataOcorrencia = Calendar.getInstance();
	
	@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ficha", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;

	@Column(name = "nu_quantidade")
	private int quantidade;

	@Column(name = "cd_id_localidade")
	private Localidade localidade;

	@Column(name = "cd_municipio")
	private String municipio;

	@Column(name = "ds_classificacaoFA")
	private ClassificacaoFA ClassificacaoFA;

	public Ficha() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Calendar dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public ClassificacaoFA getClassificacaoFA() {
		return ClassificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFA classificacaoFA) {
		ClassificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", dataOcorrencia=" + dataOcorrencia + ", animais=" + animais + ", quantidade="
				+ quantidade + ", localidade=" + localidade + ", municipio=" + municipio + ", ClassificacaoFA="
				+ ClassificacaoFA + "]";
	}

}
