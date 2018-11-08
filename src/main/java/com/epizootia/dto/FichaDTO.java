package com.epizootia.dto;

import java.util.Calendar;

public class FichaDTO {

	private int id;
	private Calendar dataOcorrencia = Calendar.getInstance();
	private AnimalDTO animal;
	private int quantidade;
	private LocalidadeDTO localidade;
	private String municipio;

	public FichaDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Calendar dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public AnimalDTO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDTO animal) {
		this.animal = animal;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalidadeDTO getLocalidade() {
		return localidade;
	}

	public void setLocalidade(LocalidadeDTO localidade) {
		this.localidade = localidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	
}
