package com.epizootia.dto;

public class CaracteristicasDTO {

	private int id;
	private Boolean natural;
	private Boolean domicilio;
	private Boolean rural;
	private Boolean urbano;

	public CaracteristicasDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getNatural() {
		return natural;
	}

	public void setNatural(Boolean natural) {
		this.natural = natural;
	}

	public Boolean getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Boolean domicilio) {
		this.domicilio = domicilio;
	}

	public Boolean getRural() {
		return rural;
	}

	public void setRural(Boolean rural) {
		this.rural = rural;
	}

	public Boolean getUrbano() {
		return urbano;
	}

	public void setUrbano(Boolean urbano) {
		this.urbano = urbano;
	}

	@Override
	public String toString() {
		return "CaracteristicasDTO [id=" + id + ", natural=" + natural + ", domicilio=" + domicilio + ", rural=" + rural
				+ ", urbano=" + urbano + "]";
	}

}
