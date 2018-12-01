package com.epizootia.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mod_epizootia_ficha")
public class Ficha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511305471275217658L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "dt_data_ocorrencia")
	private Calendar dataOcorrencia = Calendar.getInstance();

	@Transient
	/*@JsonIgnore*/
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ficha", orphanRemoval = true)
	private Set<Animal> animais;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_localidade", referencedColumnName = "cd_id")
	private Localidade localidade;

	@Column(name = "ds_municipio")
	private String municipio;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_registro_entomologico", referencedColumnName = "cd_id")
	private RegistroEntomologico registroEntomologico;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_classificacaoFA", referencedColumnName = "cd_id")
	private ClassificacaoFA classificacaoFA;

	public Ficha() {
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

	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public RegistroEntomologico getRegistroEntomologico() {
		return registroEntomologico;
	}

	public void setRegistroEntomologico(RegistroEntomologico registroEntomologico) {
		this.registroEntomologico = registroEntomologico;
	}

	public ClassificacaoFA getClassificacaoFA() {
		return classificacaoFA;
	}

	public void setClassificacaoFA(ClassificacaoFA classificacaoFA) {
		this.classificacaoFA = classificacaoFA;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", dataOcorrencia=" + dataOcorrencia + ", animais=" + animais + ", localidade="
				+ localidade + ", municipio=" + municipio + ", registroEntomologico=" + registroEntomologico
				+ ", classificacaoFA=" + classificacaoFA + "]";
	}

}
