package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_localidade")
public class Localidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3374652130920276084L;

	private int id;
	private Morador morador;
	private Double cep;
	private String bairro;
	private String logradouro;
	private String pontoReferencia;
	private Double latitude;
	private Double longitude;
	private Impactos impactos;
	private Caracteristicas caracteristicas;
	private CorposAgua corposAgua;
	private SituacaoFundiaria situacaoFundiaria;
	private RegistroEntomologico registroEntomologico;
	private String descricao;

	public Localidade() {
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

	@Column(name = "cd_morador")
	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	@Column(name = "nu_cep")
	public Double getCep() {
		return cep;
	}

	public void setCep(Double cep) {
		this.cep = cep;
	}

	@Column(name = "nm_bairro")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "nm_logradouro")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "ds_ponto_referencia")
	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	@Column(name = "nu_latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "nu_longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "cd_impactos")
	public Impactos getImpactos() {
		return impactos;
	}

	public void setImpactos(Impactos impactos) {
		this.impactos = impactos;
	}

	@Column(name = "cd_caracteristicas")
	public Caracteristicas getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Caracteristicas caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Column(name = "cd_corpos_agua")
	public CorposAgua getCorposAgua() {
		return corposAgua;
	}

	public void setCorposAgua(CorposAgua corposAgua) {
		this.corposAgua = corposAgua;
	}

	@Column(name = "cd_situacao_fundiaria")
	public SituacaoFundiaria getSituacaoFundiaria() {
		return situacaoFundiaria;
	}

	public void setSituacaoFundiaria(SituacaoFundiaria situacaoFundiaria) {
		this.situacaoFundiaria = situacaoFundiaria;
	}

	@Column(name = "cd_registro_entomologico")
	public RegistroEntomologico getRegistroEntomologico() {
		return registroEntomologico;
	}

	public void setRegistroEntomologico(RegistroEntomologico registroEntomologico) {
		this.registroEntomologico = registroEntomologico;
	}

	@Column(name = "ds_descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Localidade [id=" + id + ", morador=" + morador + ", cep=" + cep + ", bairro=" + bairro + ", logradouro="
				+ logradouro + ", pontoReferencia=" + pontoReferencia + ", latitude=" + latitude + ", longitude="
				+ longitude + ", impactos=" + impactos + ", caracteristicas=" + caracteristicas + ", corposAgua="
				+ corposAgua + ", situacaoFundiaria=" + situacaoFundiaria + ", registroEntomologico="
				+ registroEntomologico + ", descricao=" + descricao + "]";
	}
}
