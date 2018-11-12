package com.epizootia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mod_epizootia_classificacao_fa")
public class ClassificacaoFA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5713793872172775290L;
	
	private int id;
	private String classificacao;
	
	public ClassificacaoFA() {
		// TODO Auto-generated constructor stub	
	}
	
	@Id
	@Column(name = "cd_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ds_classificacao_fa")
	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	@Override
	public String toString() {
		return "ClassificacaoFA [id=" + id + ", classificacao=" + classificacao + "]";
	}

}
