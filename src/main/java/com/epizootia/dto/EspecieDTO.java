package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class EspecieDTO {

	private int cd_id;
	private String ds_especie;

	public EspecieDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getCd_id() {
		return cd_id;
	}

	public void setCd_id(int cd_id) {
		this.cd_id = cd_id;
	}

	@NotEmpty(message = "Especie não deve ser vazia")
	public String getDs_especie() {
		return ds_especie;
	}

	public void setDs_especie(String ds_especie) {
		this.ds_especie = ds_especie;
	}

	@Override
	public String toString() {
		return "EspecieDTO [cd_id=" + cd_id + ", ds_especie=" + ds_especie + "]";
	}

}
