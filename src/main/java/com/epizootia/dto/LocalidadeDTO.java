package com.epizootia.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class LocalidadeDTO {

	private int id;
	private MoradorDTO morador;
	private Double cep;
	private String bairro;
	private String logradouro;
	private String pontoReferencia;
	private Double latitude;
	private Double longitude;
	private ImpactosDTO impactos;
	private CaracteristicasDTO caracteristicas;
	private CorposAguaDTO corposAgua;
	private SituacaoFundiariaDTO situacaoFundiaria;
	private RegistroEntomologicoDTO registroEntomologico;
	private String descricao;
	
	public LocalidadeDTO() {
		// TODO Auto-generated constructor stub		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Min(value = 0, message = "Morador não  deve ser vazio")
	public MoradorDTO getMorador() {
		return morador;
	}

	public void setMorador(MoradorDTO morador) {
		this.morador = morador;
	}
	
	@NotEmpty(message = "CEP não  deve ser vazio")
	public Double getCep() {
		return cep;
	}

	public void setCep(Double cep) {
		this.cep = cep;
	}

	@NotEmpty(message = "Bairro não  deve ser vazio")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotEmpty(message = "Logradouro não  deve ser vazio")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@NotEmpty(message = "Ponto Referencia não  deve ser vazio")
	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	@NotEmpty(message = "Latitude não  deve ser vazia")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@NotEmpty(message = "Longitude não  deve ser vazia")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Min(value = 0, message = "Imapctos não  deve ser vazio")
	public ImpactosDTO getImpactos() {
		return impactos;
	}

	public void setImpactos(ImpactosDTO impactos) {
		this.impactos = impactos;
	}

	@Min(value = 0, message = "Caracteristicas não  deve ser vazio")
	public CaracteristicasDTO getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(CaracteristicasDTO caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Min(value = 0, message = "Corpos d`Água não  deve ser vazio")
	public CorposAguaDTO getCorposAgua() {
		return corposAgua;
	}

	public void setCorposAgua(CorposAguaDTO corposAgua) {
		this.corposAgua = corposAgua;
	}

	@Min(value = 0, message = "Situacao Fundiaria não  deve ser vazio")
	public SituacaoFundiariaDTO getSituacaoFundiaria() {
		return situacaoFundiaria;
	}

	public void setSituacaoFundiaria(SituacaoFundiariaDTO situacaoFundiaria) {
		this.situacaoFundiaria = situacaoFundiaria;
	}

	@Min(value = 0, message = "RegistroEntomologico não  deve ser vazio")
	public RegistroEntomologicoDTO getRegistroEntomologico() {
		return registroEntomologico;
	}

	public void setRegistroEntomologico(RegistroEntomologicoDTO registroEntomologico) {
		this.registroEntomologico = registroEntomologico;
	}

	@NotEmpty(message = "Descrição não  deve ser vazia")	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "LocalidadeDTO [id=" + id + ", morador=" + morador + ", cep=" + cep + ", bairro=" + bairro
				+ ", logradouro=" + logradouro + ", pontoReferencia=" + pontoReferencia + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", impactos=" + impactos + ", caracteristicas=" + caracteristicas
				+ ", corposAgua=" + corposAgua + ", situacaoFundiaria=" + situacaoFundiaria + ", registroEntomologico="
				+ registroEntomologico + ", descricao=" + descricao + "]";
	}
	
}
