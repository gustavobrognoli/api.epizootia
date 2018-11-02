package com.epizootia.dto;

public class CorposAguaDTO {

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

	public CorposAguaDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAcude() {
		return acude;
	}

	public void setAcude(Boolean acude) {
		this.acude = acude;
	}

	public Boolean getBanhado() {
		return banhado;
	}

	public void setBanhado(Boolean banhado) {
		this.banhado = banhado;
	}

	public Boolean getRiacho() {
		return riacho;
	}

	public void setRiacho(Boolean riacho) {
		this.riacho = riacho;
	}

	public Boolean getEstuario() {
		return estuario;
	}

	public void setEstuario(Boolean estuario) {
		this.estuario = estuario;
	}

	public Boolean getLagoa() {
		return lagoa;
	}

	public void setLagoa(Boolean lagoa) {
		this.lagoa = lagoa;
	}

	public Boolean getLago() {
		return lago;
	}

	public void setLago(Boolean lago) {
		this.lago = lago;
	}

	public Boolean getLaguna() {
		return laguna;
	}

	public void setLaguna(Boolean laguna) {
		this.laguna = laguna;
	}

	public Boolean getMangue() {
		return mangue;
	}

	public void setMangue(Boolean mangue) {
		this.mangue = mangue;
	}

	public Boolean getMar() {
		return mar;
	}

	public void setMar(Boolean mar) {
		this.mar = mar;
	}

	public Boolean getRepresa() {
		return represa;
	}

	public void setRepresa(Boolean represa) {
		this.represa = represa;
	}

	public Boolean getRio() {
		return rio;
	}

	public void setRio(Boolean rio) {
		this.rio = rio;
	}

	public Boolean getOutro() {
		return outro;
	}

	public void setOutro(Boolean outro) {
		this.outro = outro;
	}

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