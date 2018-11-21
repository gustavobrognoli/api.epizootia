package com.epizootia.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "mod_epizootia_registro_entomologico")
public class RegistroEntomologico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7672569530321767877L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "dt_data_registro")
	private Calendar DataRegistro = Calendar.getInstance();

	@Column(name = "dt_data_ultimo_registro")
	private Calendar DataUltimoRegistro = Calendar.getInstance();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_metodoCaptura", referencedColumnName = "cd_id")
	@Min(value = 0, message = "Metodo de Captura não  deve ser vazio")
	private MetodoCaptura metodoCaptura;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_equipamento", referencedColumnName = "cd_id")
	@Min(value = 0, message = "Equipamento não  deve ser vazio")
	private Equipamento equipamento;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_isolamento_viral", referencedColumnName = "cd_id")
	@Min(value = 0, message = "Isolamento Viral não  deve ser vazio")
	private IsolamentoViral isolamentoViral;

	@Column(name = "nu_cobertura_vacinal")
	@Min(value = 0, message = "Cobertura Vacinal não  deve ser vazio")
	private int coberturaVacinal;

	@Column(name = "nu_imoveis_visitados_300m")
	@Min(value = 0, message = "Imoveis Visitados não  deve ser vazio")
	private int imoveisVisitados300m;

	@Column(name = "nu_doses_aplicadas_300m")
	@Min(value = 0, message = "Doses aplicadas não  deve ser vazio")
	private int dosesAplicadas300m;

	@Column(name = "nu_focos_aedes_300m")
	@Min(value = 0, message = "Focos Aedes não  deve ser vazio")
	private int focosAedes300m;

	public RegistroEntomologico() {
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

	public MetodoCaptura getMetodoCaptura() {
		return metodoCaptura;
	}

	public void setMetodoCaptura(MetodoCaptura metodoCaptura) {
		this.metodoCaptura = metodoCaptura;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public IsolamentoViral getIsolamentoViral() {
		return isolamentoViral;
	}

	public void setIsolamentoViral(IsolamentoViral isolamentoViral) {
		this.isolamentoViral = isolamentoViral;
	}

	public int getCoberturaVacinal() {
		return coberturaVacinal;
	}

	public void setCoberturaVacinal(int coberturaVacinal) {
		this.coberturaVacinal = coberturaVacinal;
	}

	public int getImoveisVisitados300m() {
		return imoveisVisitados300m;
	}

	public void setImoveisVisitados300m(int imoveisVisitados300m) {
		this.imoveisVisitados300m = imoveisVisitados300m;
	}

	public int getDosesAplicadas300m() {
		return dosesAplicadas300m;
	}

	public void setDosesAplicadas300m(int dosesAplicadas300m) {
		this.dosesAplicadas300m = dosesAplicadas300m;
	}

	public int getFocosAedes300m() {
		return focosAedes300m;
	}

	public void setFocosAedes300m(int focosAedes300m) {
		this.focosAedes300m = focosAedes300m;
	}

	@Override
	public String toString() {
		return "RegistroEntomologico [id=" + id + ", DataRegistro=" + DataRegistro + ", DataUltimoRegistro="
				+ DataUltimoRegistro + ", metodoCaptura=" + metodoCaptura + ", equipamento=" + equipamento
				+ ", isolamentoViral=" + isolamentoViral + ", coberturaVacinal=" + coberturaVacinal + ", imoveisVisitados300m=" + imoveisVisitados300m
				+ ", dosesAplicadas300m=" + dosesAplicadas300m + ", focosAedes=" + focosAedes300m + "]";
	}

}
