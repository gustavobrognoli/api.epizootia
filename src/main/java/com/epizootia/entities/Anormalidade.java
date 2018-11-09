package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mod_epizootia_anormalidade")
public class Anormalidade implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -3247695039962861171L;
	
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
	
	private Animal animal;
	
	public Anormalidade() {
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
	
	@Column(name = "fg_anormalidade_baba")
	public Boolean getBaba() {
		return baba;
	}
	
	public void setBaba(Boolean baba) {
		this.baba = baba;
	}
	
	@Column(name = "fg_anormalidade_bicheira")
	public Boolean getBicheira() {
		return bicheira;
	}
	
	public void setBicheira(Boolean bicheira) {
		this.bicheira = bicheira;
	}
	
	@Column(name = "fg_anormalidade_caroco")
	public Boolean getCaroco() {
		return caroco;
	}
	
	public void setCaroco(Boolean caroco) {
		this.caroco = caroco;
	}
	
	@Column(name = "fg_anormalidade_cegueira")
	public Boolean getCegueira() {
		return cegueira;
	}
	
	public void setCegueira(Boolean cegueira) {
		this.cegueira = cegueira;
	}
	
	@Column(name = "fg_anormalidade_diarreia")
	public Boolean getDiarreia() {
		return diarreia;
	}
	
	public void setDiarreia(Boolean diarreia) {
		this.diarreia = diarreia;
	}
	
	@Column(name = "fg_anormalidade_fratura")
	public Boolean getFratura() {
		return fratura;
	}
	
	public void setFratura(Boolean fratura) {
		this.fratura = fratura;
	}
	
	@Column(name = "fg_anormalidade_queimadura")
	public Boolean getQueimadura() {
		return queimadura;
	}
	
	public void setQueimadura(Boolean queimadura) {
		this.queimadura = queimadura;
	}
	
	@Column(name = "fg_anormalidade_sangramento")
	public Boolean getSangramento() {
		return sangramento;
	}
	
	public void setSangramento(Boolean sangramento) {
		this.sangramento = sangramento;
	}
	
	@Column(name = "fg_anormalidade_secrecao")
	public Boolean getSecrecao() {
		return secrecao;
	}
	
	public void setSecrecao(Boolean secrecao) {
		this.secrecao = secrecao;
	}
	
	@Column(name = "ds_anormalidade")
	public String getOutraAnormalidade() {
		return outraAnormalidade;
	}
	
	public void setOutraAnormalidade(String outraAnormalidade) {
		this.outraAnormalidade = outraAnormalidade;
	}
	
	@ManyToOne(fetch= FetchType.EAGER)
	//@JoinColumn(name="cd_id")
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return "Anormalidade [id=" + id + ", baba=" + baba + ", bicheira=" + bicheira + ", caroco=" + caroco
				+ ", cegueira=" + cegueira + ", diarreia=" + diarreia + ", fratura=" + fratura + ", queimadura="
				+ queimadura + ", sangramento=" + sangramento + ", secrecao=" + secrecao + ", outraAnormalidade="
				+ outraAnormalidade + ", animal=" + animal + "]";
	}
	
}