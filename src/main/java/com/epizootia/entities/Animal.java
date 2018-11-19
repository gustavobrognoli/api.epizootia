package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_nm_popular", referencedColumnName = "cd_id")
	private NomePopular nomePopular;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_especie", referencedColumnName = "cd_id")
	private Especie especie;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_situacao", referencedColumnName = "cd_id")
	private Situacao situacao;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_sexo", referencedColumnName = "cd_id")
	@NotNull(message = "Sexo não  deve ser vazio")
	private Sexo sexo;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_idade", referencedColumnName = "cd_id")
	@NotNull(message = "Idade não  deve ser vazia")
	private Idade idade;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_apreensao", referencedColumnName = "cd_id")
	@NotNull(message = "Apreensao não  deve ser vazia")
	private Apreensao apreensao;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_vida_livre", referencedColumnName = "cd_id")
//	@NotNull(message = "Vida Livre não  deve ser vazia")
	private VidaLivre vidaLivre;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_cativeiro", referencedColumnName = "cd_id")
	@NotNull(message = "Cativeiro não  deve ser vazio")
	private Cativeiro cativeiro;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_tempoObito", referencedColumnName = "cd_id")
//	@NotNull(message = "Tempo Obito não  deve ser vazio")
	private TempoObito tempoObito;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_viscera", referencedColumnName = "cd_id")
	@NotNull(message = "Viscera não  deve ser vazia")
	private Viscera viscera;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_anormalidade", referencedColumnName = "cd_id")
	@NotNull(message = "Anormalidade não  deve ser vazia")
	private Anormalidade anormalidade;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_ficha", referencedColumnName = "cd_id")
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
	}

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
				+ ", sexo=" + sexo + ", idade=" + idade + ", apreensao=" + apreensao + ", vidaLivre=" + vidaLivre
				+ ", cativeiro=" + cativeiro + ", tempoObito=" + tempoObito + ", viscera=" + viscera + ", anormalidade="
				+ anormalidade + ", ficha=" + ficha + "]";
	}

}