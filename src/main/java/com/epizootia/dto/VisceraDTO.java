package com.epizootia.dto;

public class VisceraDTO {

	private int id;
	private Boolean figado;
	private Boolean rim;
	private Boolean cerebro;
	private Boolean baco;
	private Boolean pulmao;
	private Boolean coracao;
	private Boolean sangue;
	private Boolean soro;
	private String visceraMotivo;

	public VisceraDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getFigado() {
		return figado;
	}

	public void setFigado(Boolean figado) {
		this.figado = figado;
	}

	public Boolean getRim() {
		return rim;
	}

	public void setRim(Boolean rim) {
		this.rim = rim;
	}

	public Boolean getCerebro() {
		return cerebro;
	}

	public void setCerebro(Boolean cerebro) {
		this.cerebro = cerebro;
	}

	public Boolean getBaco() {
		return baco;
	}

	public void setBaco(Boolean baco) {
		this.baco = baco;
	}

	public Boolean getPulmao() {
		return pulmao;
	}

	public void setPulmao(Boolean pulmao) {
		this.pulmao = pulmao;
	}

	public Boolean getCoracao() {
		return coracao;
	}

	public void setCoracao(Boolean coracao) {
		this.coracao = coracao;
	}

	public Boolean getSangue() {
		return sangue;
	}

	public void setSangue(Boolean sangue) {
		this.sangue = sangue;
	}

	public Boolean getSoro() {
		return soro;
	}

	public void setSoro(Boolean soro) {
		this.soro = soro;
	}

	public String getVisceraMotivo() {
		return visceraMotivo;
	}

	public void setVisceraMotivo(String visceraMotivo) {
		this.visceraMotivo = visceraMotivo;
	}

	@Override
	public String toString() {
		return "VisceraDTO [id=" + id + ", figado=" + figado + ", rim=" + rim + ", cerebro=" + cerebro + ", baco="
				+ baco + ", pulmao=" + pulmao + ", coracao=" + coracao + ", sangue=" + sangue + ", soro=" + soro
				+ ", visceraMotivo=" + visceraMotivo + "]";
	}

}
