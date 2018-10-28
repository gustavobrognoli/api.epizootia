package com.epizootia.dto;

public class TempoObitoDTO {

	private int id;
	private String tempoObito;

	public TempoObitoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTempoObito() {
		return tempoObito;
	}

	public void setTempoObito(String tempoObito) {
		this.tempoObito = tempoObito;
	}

	@Override
	public String toString() {
		return "TempoObitoDTO [id=" + id + ", tempoObito=" + tempoObito + "]";
	}

}
