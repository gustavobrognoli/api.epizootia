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

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cd_nm_popular")
	private NomePopular nomePopular;

	@Column(name = "cd_especie")
	private Especie especie;

	@Column(name = "cd_situacao")
	private Situacao situacao;

	@Column(name = "cd_sexo")
	private Sexo sexo;

	@Column(name = "cd_idade")
	private Idade idade;

/*	
	@Column(name = "cd_apreensao")
	private Apreensao apreensao;

	@Column(name = "cd_vidaLivre")
	private VidaLivre vidaLivre;

	@Column(name = "cd_cativeiro")
	private Cativeiro cativeiro;
*/

	@Column(name = "cd_tempoObito")
	private TempoObito tempoObito;

	@Column(name = "cd_viscera")
	private Viscera viscera;

	@Column(name = "cd_anormalidade")
	private Anormalidade anormalidade;

	private Ficha ficha;

	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NomePopular getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(NomePopular nomePopular) {
		this.nomePopular = nomePopular;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Idade getIdade() {
		return idade;
	}

	public void setIdade(Idade idade) {
		this.idade = idade;
	}

/*
	public Apreensao getApreensao() {
		return apreensao;
	}

	public void setApreensao(Apreensao apreensao) {
		this.apreensao = apreensao;
	}

	public VidaLivre getVidaLivre() {
		return vidaLivre;
	}

	public void setVidaLivre(VidaLivre vidaLivre) {
		this.vidaLivre = vidaLivre;
	}

	public Cativeiro getCativeiro() {
		return cativeiro;
	}

	public void setCativeiro(Cativeiro cativeiro) {
		this.cativeiro = cativeiro;
	}*/

	public TempoObito getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(TempoObito tempoObito) {
		this.tempoObito = tempoObito;
	}

	public Viscera getViscera() {
		return viscera;
	}

	public void setViscera(Viscera viscera) {
		this.viscera = viscera;
	}

	public Anormalidade getAnormalidade() {
		return anormalidade;
	}

	public void setAnormalidade(Anormalidade anormalidade) {
		this.anormalidade = anormalidade;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nomePopular=" + nomePopular + ", especie=" + especie + ", situacao=" + situacao
				+ ", sexo=" + sexo + ", idade=" + idade + ", tempoObito=" + tempoObito + ", viscera=" + viscera
				+ ", anormalidade=" + anormalidade + ", ficha=" + ficha + "]";
	}

}
