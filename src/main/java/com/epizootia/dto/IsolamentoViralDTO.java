package com.epizootia.dto;

public class IsolamentoViralDTO {

	private int id;
	private String isolamentoViral;
	private String resultado;
	private Boolean haemagogus;
	private Boolean sabethes;
	private Boolean aegypti;
	private Boolean anopheles;
	private Boolean albopictus;

	public IsolamentoViralDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsolamentoViral() {
		return isolamentoViral;
	}

	public void setIsolamentoViral(String isolamentoViral) {
		this.isolamentoViral = isolamentoViral;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Boolean getHaemagogus() {
		return haemagogus;
	}

	public void setHaemagogus(Boolean haemagogus) {
		this.haemagogus = haemagogus;
	}

	public Boolean getSabethes() {
		return sabethes;
	}

	public void setSabethes(Boolean sabethes) {
		this.sabethes = sabethes;
	}

	public Boolean getAegypti() {
		return aegypti;
	}

	public void setAegypti(Boolean aegypti) {
		this.aegypti = aegypti;
	}

	public Boolean getAnopheles() {
		return anopheles;
	}

	public void setAnopheles(Boolean anopheles) {
		this.anopheles = anopheles;
	}

	public Boolean getAlbopictus() {
		return albopictus;
	}

	public void setAlbopictus(Boolean albopictus) {
		this.albopictus = albopictus;
	}

	@Override
	public String toString() {
		return "IsolamentoViralDTO [id=" + id + ", isolamentoViral=" + isolamentoViral + ", resultado=" + resultado
				+ ", haemagogus=" + haemagogus + ", sabethes=" + sabethes + ", aegypti=" + aegypti + ", anopheles="
				+ anopheles + ", albopictus=" + albopictus + "]";
	}

}
