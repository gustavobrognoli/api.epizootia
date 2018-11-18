package com.epizootia.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mod_epizootia_sexo")
public class Sexo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6230838597293670449L;

	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Sexo não  deve ser vazio")
	@Column(name = "ds_sexo")
	private String sexo;

/*	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sexo", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Animal> animais;
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

/*	public Set<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(Set<Animal> animais) {
		this.animais = animais;
	}
*/
	@Override
	public String toString() {
		return "Sexo [id=" + id + ", sexo=" + sexo + /*", animais=" + animais + */"]";
	}

}
