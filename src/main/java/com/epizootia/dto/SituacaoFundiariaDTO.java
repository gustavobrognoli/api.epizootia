package com.epizootia.dto;

public class SituacaoFundiariaDTO {

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

	public SituacaoFundiariaDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAssentamento() {
		return assentamento;
	}

	public void setAssentamento(Boolean assentamento) {
		this.assentamento = assentamento;
	}

	public Boolean getRural() {
		return rural;
	}

	public void setRural(Boolean rural) {
		this.rural = rural;
	}

	public Boolean getParticular() {
		return particular;
	}

	public void setParticular(Boolean particular) {
		this.particular = particular;
	}

	public Boolean getGoverno() {
		return governo;
	}

	public void setGoverno(Boolean governo) {
		this.governo = governo;
	}

	public Boolean getIndigina() {
		return indigina;
	}

	public void setIndigina(Boolean indigina) {
		this.indigina = indigina;
	}

	public Boolean getQuilombola() {
		return quilombola;
	}

	public void setQuilombola(Boolean quilombola) {
		this.quilombola = quilombola;
	}

	public Boolean getUnidadeConservacao() {
		return unidadeConservacao;
	}

	public void setUnidadeConservacao(Boolean unidadeConservacao) {
		this.unidadeConservacao = unidadeConservacao;
	}

	public int getNovaUnidadeConservacao() {
		return novaUnidadeConservacao;
	}

	public void setNovaUnidadeConservacao(int novaUnidadeConservacao) {
		this.novaUnidadeConservacao = novaUnidadeConservacao;
	}

	public Boolean getOutra() {
		return outra;
	}

	public void setOutra(Boolean outra) {
		this.outra = outra;
	}

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
