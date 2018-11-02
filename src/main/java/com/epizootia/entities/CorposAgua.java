package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_corpos_agua")
public class CorposAgua implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8135724807364797442L;

	private int id;
	private Boolean acude;
	private Boolean banhado;
	private Boolean riacho;
	private Boolean estuario;
	private Boolean lagoa;
	private Boolean lago;
	private Boolean laguna;
	private Boolean mangue;
	private Boolean mar;
	private Boolean represa;
	private Boolean rio;
	private Boolean outro;
	private String outroDescricao;

	public CorposAgua() {
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

	@Column(name = "fg_corpos_agua_acude")
	public Boolean getAcude() {
		return acude;
	}

	public void setAcude(Boolean acude) {
		this.acude = acude;
	}

	@Column(name = "fg_corpos_agua_banhado")
	public Boolean getBanhado() {
		return banhado;
	}

	public void setBanhado(Boolean banhado) {
		this.banhado = banhado;
	}

	@Column(name = "fg_corpos_agua_riacho")
	public Boolean getRiacho() {
		return riacho;
	}

	public void setRiacho(Boolean riacho) {
		this.riacho = riacho;
	}

	@Column(name = "fg_corpos_agua_estuario")
	public Boolean getEstuario() {
		return estuario;
	}

	public void setEstuario(Boolean estuario) {
		this.estuario = estuario;
	}

	@Column(name = "fg_corpos_agua_lagoa")
	public Boolean getLagoa() {
		return lagoa;
	}

	public void setLagoa(Boolean lagoa) {
		this.lagoa = lagoa;
	}

	@Column(name = "fg_corpos_agua_lago")
	public Boolean getLago() {
		return lago;
	}

	public void setLago(Boolean lago) {
		this.lago = lago;
	}

	@Column(name = "fg_corpos_agua_laguna")
	public Boolean getLaguna() {
		return laguna;
	}

	public void setLaguna(Boolean laguna) {
		this.laguna = laguna;
	}

	@Column(name = "fg_corpos_agua_mangue")
	public Boolean getMangue() {
		return mangue;
	}

	public void setMangue(Boolean mangue) {
		this.mangue = mangue;
	}

	@Column(name = "fg_corpos_agua_mar")
	public Boolean getMar() {
		return mar;
	}

	public void setMar(Boolean mar) {
		this.mar = mar;
	}

	@Column(name = "fg_corpos_agua_represa")
	public Boolean getRepresa() {
		return represa;
	}

	public void setRepresa(Boolean represa) {
		this.represa = represa;
	}

	@Column(name = "fg_corpos_agua_rio")
	public Boolean getRio() {
		return rio;
	}

	public void setRio(Boolean rio) {
		this.rio = rio;
	}

	@Column(name = "fg_corpos_agua_outro")
	public Boolean getOutro() {
		return outro;
	}

	public void setOutro(Boolean outro) {
		this.outro = outro;
	}

	@Column(name = "ds_corpos_agua_outro")
	public String getOutroDescricao() {
		return outroDescricao;
	}

	public void setOutroDescricao(String outroDescricao) {
		this.outroDescricao = outroDescricao;
	}

	@Override
	public String toString() {
		return "CorposAgua [id=" + id + ", acude=" + acude + ", banhado=" + banhado + ", riacho=" + riacho
				+ ", estuario=" + estuario + ", lagoa=" + lagoa + ", lago=" + lago + ", laguna=" + laguna + ", mangue="
				+ mangue + ", mar=" + mar + ", represa=" + represa + ", rio=" + rio + ", outro=" + outro
				+ ", outroDescricao=" + outroDescricao + "]";
	}

}
