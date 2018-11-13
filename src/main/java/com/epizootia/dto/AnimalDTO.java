package com.epizootia.dto;

import javax.validation.constraints.NotNull;

public class AnimalDTO {

	private int id;

	@NotNull(message = "Nome popular não deve ser vazio")
	private NomePopularDTO nomePopular;

	@NotNull(message = "Especie não  deve ser vazia")
	private EspecieDTO especie;

	@NotNull(message = "Situacao não  deve ser vazia")
	private SituacaoDTO situacao;

	@NotNull(message = "Anormalidade não  deve ser vazia")
	private AnormalidadeDTO anormalidade;

	@NotNull(message = "Sexo não  deve ser vazio")
	private SexoDTO sexo;

	@NotNull(message = "Idade não  deve ser vazia")
	private IdadeDTO idade;

	@NotNull(message = "Apreensão não  deve ser vazia")
	private ApreensaoDTO apreensao;

/*	
	@NotNull(message = "Vida Livre não  deve ser vazia")
	private VidaLivreDTO vidaLivre;

	@NotNull(message = "Cativeiro não  deve ser vazio")
	private CativeiroDTO cativeiro;

*/	
	/* @NotNull(message = "Tempo do Óbito não  deve ser vazio") */
	private TempoObitoDTO tempoObito;

	/* @NotNull(message = "Viscera não  deve ser vazia") */
	private VisceraDTO viscera;

	private FichaDTO ficha;

	public AnimalDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NomePopularDTO getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(NomePopularDTO nomePopular) {
		this.nomePopular = nomePopular;
	}

	public EspecieDTO getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieDTO especie) {
		this.especie = especie;
	}

	public SituacaoDTO getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDTO situacao) {
		this.situacao = situacao;
	}

	public AnormalidadeDTO getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(AnormalidadeDTO anormalidade) {
		this.anormalidade = anormalidade;
	}

	public SexoDTO getSexo() {
		return sexo;
	}

	public void setSexo(SexoDTO sexo) {
		this.sexo = sexo;
	}

	public IdadeDTO getIdade() {
		return idade;
	}

	public void setIdade(IdadeDTO idade) {
		this.idade = idade;
	}

	public ApreensaoDTO getApreensao() {
		return apreensao;
	}

	public void setApreensao(ApreensaoDTO apreensao) {
		this.apreensao = apreensao;
	}

	
/*	public VidaLivreDTO getVidaLivre() {
		return vidaLivre;
	}

	public void setVidaLivre(VidaLivreDTO vidaLivre) {
		this.vidaLivre = vidaLivre;
	}

	public CativeiroDTO getCativeiro() {
		return cativeiro;
	}

	public void setCativeiro(CativeiroDTO cativeiro) {
		this.cativeiro = cativeiro;
	}
*/
	
	public TempoObitoDTO getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(TempoObitoDTO tempoObito) {
		this.tempoObito = tempoObito;
	}

	public VisceraDTO getViscera() {
		return viscera;
	}

	public void setVisceras(VisceraDTO viscera) {
		this.viscera = viscera;
	}

	public FichaDTO getFicha() {
		return ficha;
	}

	public void setFicha(FichaDTO ficha) {
		this.ficha = ficha;
	}

	@Override
	public String toString() {
		return "AnimalDTO [id=" + id + ", nomePopular=" + nomePopular + ", especie=" + especie + ", situacao="
				+ situacao + ", anormalidade=" + anormalidade + ", sexo=" + sexo + ", idade=" + idade + ", tempoObito="
				+ tempoObito + ", viscera=" + viscera + ", ficha=" + ficha + "]";
	}
}
