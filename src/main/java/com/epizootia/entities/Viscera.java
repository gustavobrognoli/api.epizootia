package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_visceras")
public class Viscera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195875444412713276L;

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

	public Viscera() {
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

	@Column(name = "fg_visceras_figado")
	public Boolean getFigado() {
		return figado;
	}

	public void setFigado(Boolean figado) {
		this.figado = figado;
	}

	@Column(name = "fg_visceras_rim")
	public Boolean getRim() {
		return rim;
	}

	public void setRim(Boolean rim) {
		this.rim = rim;
	}

	@Column(name = "fg_visceras_cerebro")
	public Boolean getCerebro() {
		return cerebro;
	}

	public void setCerebro(Boolean cerebro) {
		this.cerebro = cerebro;
	}

	@Column(name = "fg_visceras_baco")
	public Boolean getBaco() {
		return baco;
	}

	public void setBaco(Boolean baco) {
		this.baco = baco;
	}

	@Column(name = "fg_visceras_pulmao")
	public Boolean getPulmao() {
		return pulmao;
	}

	public void setPulmao(Boolean pulmao) {
		this.pulmao = pulmao;
	}

	@Column(name = "fg_visceras_coracao")
	public Boolean getCoracao() {
		return coracao;
	}

	public void setCoracao(Boolean coracao) {
		this.coracao = coracao;
	}

	@Column(name = "fg_visceras_sangue")
	public Boolean getSangue() {
		return sangue;
	}

	public void setSangue(Boolean sangue) {
		this.sangue = sangue;
	}

	@Column(name = "fg_visceras_soro")
	public Boolean getSoro() {
		return soro;
	}

	public void setSoro(Boolean soro) {
		this.soro = soro;
	}

	@Column(name = "ds_visceras_motivo")
	public String getVisceraMotivo() {
		return visceraMotivo;
	}

	public void setVisceraMotivo(String visceraMotivo) {
		this.visceraMotivo = visceraMotivo;
	}

	@Override
	public String toString() {
		return "Viscera [id=" + id + ", figado=" + figado + ", rim=" + rim + ", cerebro=" + cerebro + ", baco=" + baco
				+ ", pulmao=" + pulmao + ", coracao=" + coracao + ", sangue=" + sangue + ", soro=" + soro
				+ ", visceraMotivo=" + visceraMotivo + "]";
	}

}
