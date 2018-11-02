package com.epizootia.dto;

import javax.validation.constraints.NotEmpty;

public class MetodoCapturaDTO {

	private int id;
	private String metodoCaptura;

	public MetodoCapturaDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message = "Metodo de Captura não  deve ser vazio")
	public String getMetodoCaptura() {
		return metodoCaptura;
	}

	public void setMetodoCaptura(String metodoCaptura) {
		this.metodoCaptura = metodoCaptura;
	}

	@Override
	public String toString() {
		return "MetodoCapturaDTO [id=" + id + ", metodoCaptura=" + metodoCaptura + "]";
	}

}
