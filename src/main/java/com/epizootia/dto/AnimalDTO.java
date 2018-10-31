package com.epizootia.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AnimalDTO  {

	private int id;
	private int nomePopular;
	private EspecieDTO especie;
	private int situacao;
	private int anormalidade;
	private int sexo;
	private int idade;
	private Boolean apreensao;
	private Boolean vidaLivre;
	private Boolean cativeiro;
	private int tempoObito;
	private int visceras;
	private int classificacaoFA;

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
	public int getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(int nomePopular) {
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
	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	@Min(value = 0, message = "Anormalidade não  deve ser vazia")
	public int getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(int anormalidade) {
		this.anormalidade = anormalidade;
	}

	@Min(value = 0, message = "Sexo não  deve ser vazio")
	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	@Min(value = 0, message = "Idade não  deve ser vazia")
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
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
	public int getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(int tempoObito) {
		this.tempoObito = tempoObito;
	}

	@Min(value = 0, message = "Coleta de Visceras não  deve ser vazia")
	public int getVisceras() {
		return visceras;
	}

	public void setVisceras(int visceras) {
		this.visceras = visceras;
	}

	@Min(value = 0, message = "Classificação de Febre Amarela não  deve ser vazia")
	public int getClassificacaoFA() {
		return classificacaoFA;
	}

	public void setClassificacaoFA(int classificacaoFA) {
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