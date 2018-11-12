package com.epizootia.dto;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;

public class FichaDTO {

	private int id;
	private Calendar dataOcorrencia = Calendar.getInstance();
	private List <AnimalDTO> animais;
	
	private int quantidade;
	private LocalidadeDTO localidade;
	private String municipio;
	private ClassificacaoFADTO ClassificacaoFA;

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

	@Column(name = "cd_id_animal")
	public List<AnimalDTO> getAnimais() {
		return animais;
	}

	public void setAnimais(List<AnimalDTO> animais) {
		this.animais = animais;
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
	
	public ClassificacaoFADTO getClassificacaoFA() {
		return ClassificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFADTO classificacaoFA) {
		ClassificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", dataOcorrencia=" + dataOcorrencia + ", animais=" + animais + ", quantidade="
				+ quantidade + ", localidade=" + localidade + ", municipio=" + municipio + ", ClassificacaoFA="
				+ ClassificacaoFA + "]";
	}
}
