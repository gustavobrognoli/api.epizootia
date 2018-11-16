package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@NotNull(message = "Nome popular não deve ser vazio")
	@Column(name = "cd_nm_popular")
	private NomePopular nomePopular;

	@NotNull(message = "Especie não  deve ser vazia")
	@Column(name = "cd_especie")
	private Especie especie;

	@NotNull(message = "Situação não  deve ser vazia")
	@Column(name = "cd_situacao")
	private Situacao situacao;

	@NotNull(message = "Sexo não  deve ser vazio")
	@Column(name = "cd_sexo")
	private Sexo sexo;

	@NotNull(message = "Idade não  deve ser vazia")
	@Column(name = "cd_idade")
	private Idade idade;

/*	@NotNull(message = "Apreensão não  deve ser vazia")
	@Column(name = "cd_apreensao")
	private Apreensao apreensao;

	@NotNull(message = "Vida Livre não  deve ser vazia")
	@Column(name = "cd_vidaLivre")
	private VidaLivre vidaLivre;

	@NotNull(message = "Cativeiro não  deve ser vazio")
	@Column(name = "cd_cativeiro")
	private Cativeiro cativeiro;
*/

	/* @NotNull(message = "Tempo do Óbito não  deve ser vazio") */
	@Column(name = "cd_tempoObito")
	private TempoObito tempoObito;

	/* @NotNull(message = "Viscera não  deve ser vazia") */
	@Column(name = "cd_viscera")
	private Viscera viscera;

	@NotNull(message = "Anormalidade não  deve ser vazia")
	@Column(name = "cd_anormalidade")
	private Anormalidade anormalidade;

	@ManyToOne
    @JoinColumn(name = "cd_id_ficha")
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