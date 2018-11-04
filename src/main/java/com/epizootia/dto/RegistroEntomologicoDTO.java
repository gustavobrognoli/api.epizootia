package com.epizootia.dto;

import java.util.Calendar;

import javax.validation.constraints.Min;

public class RegistroEntomologicoDTO {

	private int id;
	private Calendar DataRegistro = Calendar.getInstance();
	private Calendar DataUltimoRegistro = Calendar.getInstance();
	private MetodoCapturaDTO metodoCaptura;
	private EquipamentoDTO equipamento;
	private IsolamentoViralDTO isolamentoViral;
	private RecomendacaoVacinalDTO recomendacaoVacinal;
	private int coberturaVacinal;
	private int imoveisVisitados300m;
	private int dosesAplicadas300m;
	private int focosAedes300m;

	public RegistroEntomologicoDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataRegistro() {
		return DataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		DataRegistro = dataRegistro;
	}

	public Calendar getDataUltimoRegistro() {
		return DataUltimoRegistro;
	}

	public void setDataUltimoRegistro(Calendar dataUltimoRegistro) {
		DataUltimoRegistro = dataUltimoRegistro;
	}

	@Min(value = 0, message = "Metodo de Captura não  deve ser vazio")
	public MetodoCapturaDTO getMetodoCaptura() {
		return metodoCaptura;
	}

	public void setMetodoCaptura(MetodoCapturaDTO metodoCaptura) {
		this.metodoCaptura = metodoCaptura;
	}

	@Min(value = 0, message = "Equipamento não  deve ser vazio")
	public EquipamentoDTO getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(EquipamentoDTO equipamento) {
		this.equipamento = equipamento;
	}

	@Min(value = 0, message = "Isolamento Viral não  deve ser vazio")
	public IsolamentoViralDTO getIsolamentoViral() {
		return isolamentoViral;
	}

	public void setIsolamentoViral(IsolamentoViralDTO isolamentoViral) {
		this.isolamentoViral = isolamentoViral;
	}

	@Min(value = 0, message = "Recomendação Viral não  deve ser vazio")
	public RecomendacaoVacinalDTO getRecomendacaoVacinal() {
		return recomendacaoVacinal;
	}

	public void setRecomendacaoVacinal(RecomendacaoVacinalDTO recomendacaoVacinal) {
		this.recomendacaoVacinal = recomendacaoVacinal;
	}

	@Min(value = 0, message = "Cobertura Vacinal não  deve ser vazio")
	public int getCoberturaVacinal() {
		return coberturaVacinal;
	}

	public void setCoberturaVacinal(int coberturaVacinal) {
		this.coberturaVacinal = coberturaVacinal;
	}

	@Min(value = 0, message = "Imoveis Visitados não  deve ser vazio")
	public int getImoveisVisitados300m() {
		return imoveisVisitados300m;
	}

	public void setImoveisVisitados300m(int imoveisVisitados300m) {
		this.imoveisVisitados300m = imoveisVisitados300m;
	}

	@Min(value = 0, message = "Doses aplicadas não  deve ser vazio")
	public int getDosesAplicadas300m() {
		return dosesAplicadas300m;
	}

	public void setDosesAplicadas300m(int dosesAplicadas300m) {
		this.dosesAplicadas300m = dosesAplicadas300m;
	}

	@Min(value = 0, message = "Focos Aedes não  deve ser vazio")
	public int getFocosAedes300m() {
		return focosAedes300m;
	}

	public void setFocosAedes300m(int focosAedes300m) {
		this.focosAedes300m = focosAedes300m;
	}
}
