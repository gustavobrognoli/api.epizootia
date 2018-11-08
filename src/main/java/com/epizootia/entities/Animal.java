package com.epizootia.entities;

import java.io.Serializable;
import java.util.List;

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
	private NomePopular nomePopular;
	private Especie especie;
	private Situacao situacao;
	// OneToMany
	private List<Anormalidade> anormalidades;
	private Sexo sexo;
	private Idade idade;
	private Boolean apreensao;
	private Boolean vidaLivre;
	private Boolean cativeiro;
	private TempoObito tempoObito;
	private Viscera visceras;
	private ClassificacaoFA classificacaoFA;

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
	public NomePopular getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(NomePopular nomePopular) {
		this.nomePopular = nomePopular;
	}

	@Column(name = "cd_especie")
	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	@Column(name="cd_situacao")
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao2) {
		this.situacao = situacao2;
	}

	@Column(name="cd_anormalidade")
	public List<Anormalidade> getAnormalidades() {
		return anormalidades;
	}

	public void setAnormalidades(List<Anormalidade> anormalidades) {
		this.anormalidades = anormalidades;
	}

	@Column(name = "cd_sexo")
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	@Column(name= "cd_idade")
	public Idade getIdade() {
		return idade;
	}

	public void setIdade(Idade idade) {
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
	public TempoObito getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(TempoObito tempoObito) {
		this.tempoObito = tempoObito;
	}

	@Column(name="cd_visceras")
	public Viscera getVisceras() {
		return visceras;
	}

	public void setVisceras(Viscera visceras) {
		this.visceras = visceras;
	}

	@Column(name="cd_classificacao_fa")
	public ClassificacaoFA getClassificacaoFA() {
		return classificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFA classificacaoFA) {
		this.classificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nomePopular=" + nomePopular + ", especie=" + especie + ", situacao=" + situacao
				+ ", anormalidade=" + anormalidades + ", sexo=" + sexo + ", idade=" + idade + ", apreensao=" + apreensao
				+ ", vidaLivre=" + vidaLivre + ", cativeiro=" + cativeiro + ", tempoObito=" + tempoObito + ", visceras="
				+ visceras + ", classificacaoFA=" + classificacaoFA + "]";
	}

}
