package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mod_epizootia_localidade")
public class Localidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3374652130920276084L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Min(value = 0, message = "Morador não  deve ser vazio")
	private Morador morador;
	
	@NotEmpty(message = "CEP não  deve ser vazio")
	private Double cep;
	
	@NotEmpty(message = "Bairro não  deve ser vazio")
	private String bairro;
	
	@NotEmpty(message = "Logradouro não  deve ser vazio")
	private String logradouro;
	
	@NotEmpty(message = "Ponto Referencia não  deve ser vazio")
	private String pontoReferencia;
	
	@NotEmpty(message = "Latitude não  deve ser vazia")
	private Double latitude;
	
	@NotEmpty(message = "Longitude não  deve ser vazia")
	private Double longitude;
	
	private Impacto impactos;
	
	@Min(value = 0, message = "Caracteristicas não  deve ser vazio")
	private Caracteristica caracteristica;
	
	/*@Min(value = 0, message = "Corpos d`Água não  deve ser vazio")*/
	private CorposAgua corposAgua;
	
	/*@Min(value = 0, message = "Situacao Fundiaria não  deve ser vazio")*/
	private SituacaoFundiaria situacaoFundiaria;
	
	@Min(value = 0, message = "Registro Entomologico não  deve ser vazio")
	private RegistroEntomologico registroEntomologico;
	
	@NotEmpty(message = "Descrição não  deve ser vazia")	
	private String descricao;

	public Localidade() {
		// TODO Auto-generated constructor stub
	}


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
	public Impacto getImpactos() {
		return impactos;
	}

	public void setImpactos(Impacto impactos) {
		this.impactos = impactos;
	}

	@Column(name = "cd_caracteristicas")
	public Caracteristica getCaracteristicas() {
		return caracteristica;
	}

	public void setCaracteristicas(Caracteristica caracteristicas) {
		this.caracteristica = caracteristicas;
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
				+ longitude + ", impactos=" + impactos + ", caracteristicas=" + caracteristica + ", corposAgua="
				+ corposAgua + ", situacaoFundiaria=" + situacaoFundiaria + ", registroEntomologico="
				+ registroEntomologico + ", descricao=" + descricao + "]";
	}
}
