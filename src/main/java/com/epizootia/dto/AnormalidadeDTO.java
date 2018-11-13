package com.epizootia.dto;

public class AnormalidadeDTO {

	private int id;
	private String sintoma;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public String toString() {
		return "AnormalidadeDTO [id=" + id + ", sintoma=" + sintoma + "]";
	}

}
