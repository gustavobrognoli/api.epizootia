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
	private Boolean confirmado;
	private Boolean descartado;
	private Boolean ignorado;
	
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
	
	@Column(name = "fg_classificacao_fa_confirmado")
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
	
	@Column(name = "fg_classificacao_fa_descartado")
	public Boolean getDescartado() {
		return descartado;
	}
	public void setDescartado(Boolean descartado) {
		this.descartado = descartado;
	}
	
	@Column(name = "fg_classificacao_fa_ignorado")
	public Boolean getIgnorado() {
		return ignorado;
	}
	public void setIgnorado(Boolean ignorado) {
		this.ignorado = ignorado;
	}
	@Override
	public String toString() {
		return "ClassificacaoFA [id=" + id + ", confirmado=" + confirmado + ", descartado=" + descartado + ", ignorado="
				+ ignorado + "]";
	}
	
}
