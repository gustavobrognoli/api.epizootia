package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnormalidadeDTO {

	private int id;
	private Boolean baba;
	private Boolean bicheira;
	private Boolean caroco;
	private Boolean cegueira;
	private Boolean diarreia;
	private Boolean fratura;
	private Boolean queimadura;
	private Boolean sangramento;
	private Boolean secrecao;
	private String outraAnormalidade;
	
	public AnormalidadeDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull(message = "Baba não  deve ser vazia")
	public Boolean getBaba() {
		return baba;
	}

	public void setBaba(Boolean baba) {
		this.baba = baba;
	}

	@NotNull(message = "Bicheira não  deve ser vazia")
	public Boolean getBicheira() {
		return bicheira;
	}

	public void setBicheira(Boolean bicheira) {
		this.bicheira = bicheira;
	}

	@NotNull(message = "Caroco não  deve ser vazio")
	public Boolean getCaroco() {
		return caroco;
	}

	public void setCaroco(Boolean caroco) {
		this.caroco = caroco;
	}

	@NotNull(message = "Cegueira não  deve ser vazia")
	public Boolean getCegueira() {
		return cegueira;
	}

	public void setCegueira(Boolean cegueira) {
		this.cegueira = cegueira;
	}

	@NotNull(message = "Diarreia não  deve ser vazia")
	public Boolean getDiarreia() {
		return diarreia;
	}

	public void setDiarreia(Boolean diarreia) {
		this.diarreia = diarreia;
	}

	@NotNull(message = "Fratura não  deve ser vazia")
	public Boolean getFratura() {
		return fratura;
	}

	public void setFratura(Boolean fratura) {
		this.fratura = fratura;
	}

	@NotNull(message = "Qurimadura não  deve ser vazia")
	public Boolean getQueimadura() {
		return queimadura;
	}

	public void setQueimadura(Boolean queimadura) {
		this.queimadura = queimadura;
	}

	@NotNull(message = "Sangramento não  deve ser vazia")
	public Boolean getSangramento() {
		return sangramento;
	}

	public void setSangramento(Boolean sangramento) {
		this.sangramento = sangramento;
	}

	@NotNull(message = "Secrecao não  deve ser vazia")
	public Boolean getSecrecao() {
		return secrecao;
	}

	public void setSecrecao(Boolean secrecao) {
		this.secrecao = secrecao;
	}

	@NotEmpty(message = "Outra Anormalidade não  deve ser vazia")
	public String getOutraAnormalidade() {
		return outraAnormalidade;
	}

	public void setOutraAnormalidade(String outraAnormalidade) {
		this.outraAnormalidade = outraAnormalidade;
	}

	@Override
	public String toString() {
		return "AnormalidadeDTO [id=" + id + ", baba=" + baba + ", bicheira=" + bicheira + ", caroco=" + caroco
				+ ", cegueira=" + cegueira + ", diarreia=" + diarreia + ", fratura=" + fratura + ", queimadura="
				+ queimadura + ", sangramento=" + sangramento + ", secrecao=" + secrecao + ", outraAnormalidade="
				+ outraAnormalidade + "]";
	}
}
