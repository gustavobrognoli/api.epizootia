package com.epizootia.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AnimalDTO  {

	private int id;
	private NomePopularDTO nomePopular;
	private EspecieDTO especie;
	private SituacaoDTO situacao;
	private AnormalidadeDTO anormalidade;
	private SexoDTO sexo;
	private IdadeDTO idade;
	private Boolean apreensao;
	private Boolean vidaLivre;
	private Boolean cativeiro;
	private TempoObitoDTO tempoObito;
	private VisceraDTO visceras;
	private ClassificacaoFADTO classificacaoFA;

	public AnimalDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Min(value = 0, message = "Nome popular não  deve ser vazio")
	public NomePopularDTO getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(NomePopularDTO nomePopular) {
		this.nomePopular = nomePopular;
	}

	@Min(value = 0, message = "Especie não  deve ser vazia")
	public EspecieDTO getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieDTO especie) {
		this.especie = especie;
	}

	@Min(value = 0, message = "Situaçao não  deve ser vazia")
	public SituacaoDTO getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDTO situacao) {
		this.situacao = situacao;
	}

	@Min(value = 0, message = "Anormalidade não  deve ser vazia")
	public AnormalidadeDTO getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(AnormalidadeDTO anormalidade) {
		this.anormalidade = anormalidade;
	}

	@Min(value = 0, message = "Sexo não  deve ser vazio")
	public SexoDTO getSexo() {
		return sexo;
	}

	public void setSexo(SexoDTO sexo) {
		this.sexo = sexo;
	}

	@Min(value = 0, message = "Idade não  deve ser vazia")
	public IdadeDTO getIdade() {
		return idade;
	}

	public void setIdade(IdadeDTO idade) {
		this.idade = idade;
	}

	@NotNull(message = "Apreensão não  deve ser vazia")
	public Boolean getApreensao() {
		return apreensao;
	}

	public void setApreensao(Boolean apreensao) {
		this.apreensao = apreensao;
	}

	@NotNull(message = "Vida Livre não  deve ser vazia")
	public Boolean getVidaLivre() {
		return vidaLivre;
	}

	public void setVidaLivre(Boolean vidaLivre) {
		this.vidaLivre = vidaLivre;
	}

	@NotNull(message = "Cativeiro não  deve ser vazio")
	public Boolean getCativeiro() {
		return cativeiro;
	}

	public void setCativeiro(Boolean cativeiro) {
		this.cativeiro = cativeiro;
	}

	@Min(value = 0, message = "Tempo do Óbito não  deve ser vazio")
	public TempoObitoDTO getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(TempoObitoDTO tempoObito) {
		this.tempoObito = tempoObito;
	}

	@Min(value = 0, message = "Coleta de Visceras não  deve ser vazia")
	public VisceraDTO getVisceras() {
		return visceras;
	}

	public void setVisceras(VisceraDTO visceras) {
		this.visceras = visceras;
	}

	@Min(value = 0, message = "Classificação de Febre Amarela não  deve ser vazia")
	public ClassificacaoFADTO getClassificacaoFA() {
		return classificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFADTO classificacaoFA) {
		this.classificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "AnimalDTO [id=" + id + ", nomePopular=" + nomePopular + ", especie=" + especie + ", situacao="
				+ situacao + ", anormalidade=" + anormalidade + ", sexo=" + sexo + ", idade=" + idade + ", apreensao="
				+ apreensao + ", vidaLivre=" + vidaLivre + ", cativeiro=" + cativeiro + ", tempoObito=" + tempoObito
				+ ", visceras=" + visceras + ", classificacaoFA=" + classificacaoFA + "]";
	}

}