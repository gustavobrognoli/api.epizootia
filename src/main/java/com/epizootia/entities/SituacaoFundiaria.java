package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_sit_fundiaria")
public class SituacaoFundiaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707253211515908961L;

	private int id;
	private Boolean assentamento;
	private Boolean rural;
	private Boolean particular;
	private Boolean governo;
	private Boolean indigina;
	private Boolean quilombola;
	private Boolean unidadeConservacao;
	private int novaUnidadeConservacao;
	private Boolean outra;
	private String outraDescricao;

	public SituacaoFundiaria() {
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

	@Column(name = "fg_st_fundiaria_assentamento")
	public Boolean getAssentamento() {
		return assentamento;
	}

	public void setAssentamento(Boolean assentamento) {
		this.assentamento = assentamento;
	}

	@Column(name = "fg_st_fundiaria_rural")
	public Boolean getRural() {
		return rural;
	}

	public void setRural(Boolean rural) {
		this.rural = rural;
	}

	@Column(name = "fg_st_fundiaria_particular")
	public Boolean getParticular() {
		return particular;
	}

	public void setParticular(Boolean particular) {
		this.particular = particular;
	}

	@Column(name = "fg_st_fundiaria_governo")
	public Boolean getGoverno() {
		return governo;
	}

	public void setGoverno(Boolean governo) {
		this.governo = governo;
	}

	@Column(name = "fg_st_fundiaria_indigina")
	public Boolean getIndigina() {
		return indigina;
	}

	public void setIndigina(Boolean indigina) {
		this.indigina = indigina;
	}

	@Column(name = "fg_st_fundiaria_quilombola")
	public Boolean getQuilombola() {
		return quilombola;
	}

	public void setQuilombola(Boolean quilombola) {
		this.quilombola = quilombola;
	}

	@Column(name = "fg_st_fundiaria_unidade_conservacao")
	public Boolean getUnidadeConservacao() {
		return unidadeConservacao;
	}

	public void setUnidadeConservacao(Boolean unidadeConservacao) {
		this.unidadeConservacao = unidadeConservacao;
	}

	@Column(name = "cd_st_fundiaria_unidade_conservacao")
	public int getNovaUnidadeConservacao() {
		return novaUnidadeConservacao;
	}

	public void setNovaUnidadeConservacao(int novaUnidadeConservacao) {
		this.novaUnidadeConservacao = novaUnidadeConservacao;
	}

	@Column(name = "fg_st_fundiaria_unidade_outros")
	public Boolean getOutra() {
		return outra;
	}

	public void setOutra(Boolean outra) {
		this.outra = outra;
	}

	@Column(name = "ds_st_fundiaria_outros")
	public String getOutraDescricao() {
		return outraDescricao;
	}

	public void setOutraDescricao(String outraDescricao) {
		this.outraDescricao = outraDescricao;
	}

	@Override
	public String toString() {
		return "SituacaoFundiaria [id=" + id + ", assentamento=" + assentamento + ", rural=" + rural + ", particular="
				+ particular + ", governo=" + governo + ", indigina=" + indigina + ", quilombola=" + quilombola
				+ ", unidadeConservacao=" + unidadeConservacao + ", novaUnidadeConservacao=" + novaUnidadeConservacao
				+ ", outra=" + outra + ", outraDescricao=" + outraDescricao + "]";
	}

}
