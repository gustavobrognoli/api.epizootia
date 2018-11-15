package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_impactos_observados")
public class Impacto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7951258089497633768L;

	private int id;
	private Boolean assentamentos;
	private Boolean alteracaoRio;
	private Boolean avancoAgropecuario;
	private Boolean desastres;
	private Boolean desmatamento;
	private Boolean petroleo;
	private Boolean obras;
	private Boolean acidentais;
	private Boolean imoveis;
	private Boolean incendio;
	private Boolean turismo;
	private Boolean urbanizacao;
	private Boolean impactosOutro;
	private String outroImpacto;

	public Impacto() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "fg_impactos_assentamentos")
	public Boolean getAssentamentos() {
		return assentamentos;
	}

	public void setAssentamentos(Boolean assentamentos) {
		this.assentamentos = assentamentos;
	}

	@Column(name = "fg_impactos_alteracao_rio")
	public Boolean getAlteracaoRio() {
		return alteracaoRio;
	}

	public void setAlteracaoRio(Boolean alteracaoRio) {
		this.alteracaoRio = alteracaoRio;
	}

	@Column(name = "fg_impactos_avanco_agropecuario")
	public Boolean getAvancoAgropecuario() {
		return avancoAgropecuario;
	}

	public void setAvancoAgropecuario(Boolean avancoAgropecuario) {
		this.avancoAgropecuario = avancoAgropecuario;
	}

	@Column(name = "fg_impactos_desastres")
	public Boolean getDesastres() {
		return desastres;
	}

	public void setDesastres(Boolean desastres) {
		this.desastres = desastres;
	}

	@Column(name = "fg_impactos_desmatamento")
	public Boolean getDesmatamento() {
		return desmatamento;
	}

	public void setDesmatamento(Boolean desmatamento) {
		this.desmatamento = desmatamento;
	}

	@Column(name = "fg_impactos_petroleo")
	public Boolean getPetroleo() {
		return petroleo;
	}

	public void setPetroleo(Boolean petroleo) {
		this.petroleo = petroleo;
	}

	@Column(name = "fg_impactos_obras")
	public Boolean getObras() {
		return obras;
	}

	public void setObras(Boolean obras) {
		this.obras = obras;
	}

	@Column(name = "fg_impactos_acidentais")
	public Boolean getAcidentais() {
		return acidentais;
	}

	public void setAcidentais(Boolean acidentais) {
		this.acidentais = acidentais;
	}

	@Column(name = "fg_impactos_imoveis")
	public Boolean getImoveis() {
		return imoveis;
	}

	public void setImoveis(Boolean imoveis) {
		this.imoveis = imoveis;
	}

	@Column(name = "fg_impactos_incendio")
	public Boolean getIncendio() {
		return incendio;
	}

	public void setIncendio(Boolean incendio) {
		this.incendio = incendio;
	}

	@Column(name = "fg_impactos_turismo")
	public Boolean getTurismo() {
		return turismo;
	}

	public void setTurismo(Boolean turismo) {
		this.turismo = turismo;
	}

	@Column(name = "fg_impactos_urbanizacao")
	public Boolean getUrbanizacao() {
		return urbanizacao;
	}

	public void setUrbanizacao(Boolean urbanizacao) {
		this.urbanizacao = urbanizacao;
	}

	@Column(name = "fg_impactos_outros")
	public Boolean getImpactosOutro() {
		return impactosOutro;
	}

	public void setImpactosOutro(Boolean impactosOutro) {
		this.impactosOutro = impactosOutro;
	}

	@Column(name = "ds_impactos_outros")
	public String getOutroImpacto() {
		return outroImpacto;
	}

	public void setOutroImpacto(String outroImpacto) {
		this.outroImpacto = outroImpacto;
	}

	@Override
	public String toString() {
		return "Impactos [id=" + id + ", assentamentos=" + assentamentos + ", alteracaoRio=" + alteracaoRio
				+ ", avancoAgropecuario=" + avancoAgropecuario + ", desastres=" + desastres + ", desmatamento="
				+ desmatamento + ", petroleo=" + petroleo + ", obras=" + obras + ", acidentais=" + acidentais
				+ ", imoveis=" + imoveis + ", incendio=" + incendio + ", turismo=" + turismo + ", urbanizacao="
				+ urbanizacao + ", impactosOutro=" + impactosOutro + ", outroImpacto=" + outroImpacto + "]";
	}

}
