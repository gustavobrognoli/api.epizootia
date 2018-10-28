package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_animal")
public class Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8669089949751249376L;

	private int id;
	private int nomePopular;
	private int especie;
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

	public Animal() {
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

	@Column(name = "cd_nm_popular")
	public int getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(int nomePopular) {
		this.nomePopular = nomePopular;
	}

	@Column(name = "cd_especie")
	public int getEspecie() {
		return especie;
	}

	public void setEspecie(int especie) {
		this.especie = especie;
	}

	@Column(name="cd_situacao")
	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	@Column(name="cd_anormalidade")
	public int getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(int anormalidade) {
		this.anormalidade = anormalidade;
	}

	@Column(name = "cd_sexo")
	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	@Column(name= "cd_idade")
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Column(name = "fg_apreensao")
	public Boolean getApreensao() {
		return apreensao;
	}

	public void setApreensao(Boolean apreensao) {
		this.apreensao = apreensao;
	}

	@Column(name = "fg_vidaLivre")
	public Boolean getVidaLivre() {
		return vidaLivre;
	}

	public void setVidaLivre(Boolean vidaLivre) {
		this.vidaLivre = vidaLivre;
	}

	@Column(name= "fg_cativeiro")
	public Boolean getCativeiro() {
		return cativeiro;
	}

	public void setCativeiro(Boolean cativeiro) {
		this.cativeiro = cativeiro;
	}

	@Column(name="cd_tempo_obito")
	public int getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(int tempoObito) {
		this.tempoObito = tempoObito;
	}

	@Column(name="cd_visceras")
	public int getVisceras() {
		return visceras;
	}

	public void setVisceras(int visceras) {
		this.visceras = visceras;
	}

	@Column(name="cd_classificacao_fa")
	public int getClassificacaoFA() {
		return classificacaoFA;
	}

	public void setClassificacaoFA(int classificacaoFA) {
		this.classificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nomePopular=" + nomePopular + ", especie=" + especie + ", situacao=" + situacao
				+ ", anormalidade=" + anormalidade + ", sexo=" + sexo + ", idade=" + idade + ", apreensao=" + apreensao
				+ ", vidaLivre=" + vidaLivre + ", cativeiro=" + cativeiro + ", tempoObito=" + tempoObito + ", visceras="
				+ visceras + ", classificacaoFA=" + classificacaoFA + "]";
	}

}
