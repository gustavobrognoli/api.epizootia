package com.epizootia.dto;

public class ImpactosDTO {

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

	public ImpactosDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAssentamentos() {
		return assentamentos;
	}

	public void setAssentamentos(Boolean assentamentos) {
		this.assentamentos = assentamentos;
	}

	public Boolean getAlteracaoRio() {
		return alteracaoRio;
	}

	public void setAlteracaoRio(Boolean alteracaoRio) {
		this.alteracaoRio = alteracaoRio;
	}

	public Boolean getAvancoAgropecuario() {
		return avancoAgropecuario;
	}

	public void setAvancoAgropecuario(Boolean avancoAgropecuario) {
		this.avancoAgropecuario = avancoAgropecuario;
	}

	public Boolean getDesastres() {
		return desastres;
	}

	public void setDesastres(Boolean desastres) {
		this.desastres = desastres;
	}

	public Boolean getDesmatamento() {
		return desmatamento;
	}

	public void setDesmatamento(Boolean desmatamento) {
		this.desmatamento = desmatamento;
	}

	public Boolean getPetroleo() {
		return petroleo;
	}

	public void setPetroleo(Boolean petroleo) {
		this.petroleo = petroleo;
	}

	public Boolean getObras() {
		return obras;
	}

	public void setObras(Boolean obras) {
		this.obras = obras;
	}

	public Boolean getAcidentais() {
		return acidentais;
	}

	public void setAcidentais(Boolean acidentais) {
		this.acidentais = acidentais;
	}

	public Boolean getImoveis() {
		return imoveis;
	}

	public void setImoveis(Boolean imoveis) {
		this.imoveis = imoveis;
	}

	public Boolean getIncendio() {
		return incendio;
	}

	public void setIncendio(Boolean incendio) {
		this.incendio = incendio;
	}

	public Boolean getTurismo() {
		return turismo;
	}

	public void setTurismo(Boolean turismo) {
		this.turismo = turismo;
	}

	public Boolean getUrbanizacao() {
		return urbanizacao;
	}

	public void setUrbanizacao(Boolean urbanizacao) {
		this.urbanizacao = urbanizacao;
	}

	public Boolean getImpactosOutro() {
		return impactosOutro;
	}

	public void setImpactosOutro(Boolean impactosOutro) {
		this.impactosOutro = impactosOutro;
	}

	public String getOutroImpacto() {
		return outroImpacto;
	}

	public void setOutroImpacto(String outroImpacto) {
		this.outroImpacto = outroImpacto;
	}

	@Override
	public String toString() {
		return "ImpactosDTO [id=" + id + ", assentamentos=" + assentamentos + ", alteracaoRio=" + alteracaoRio
				+ ", avancoAgropecuario=" + avancoAgropecuario + ", desastres=" + desastres + ", desmatamento="
				+ desmatamento + ", petroleo=" + petroleo + ", obras=" + obras + ", acidentais=" + acidentais
				+ ", imoveis=" + imoveis + ", incendio=" + incendio + ", turismo=" + turismo + ", urbanizacao="
				+ urbanizacao + ", impactosOutro=" + impactosOutro + ", outroImpacto=" + outroImpacto + "]";
	}

}
