package com.epizootia.dto;

public class ClassificacaoFADTO {

	private int id;
	private Boolean confirmado;
	private Boolean descartado;
	private Boolean ignorado;

	public ClassificacaoFADTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Boolean getDescartado() {
		return descartado;
	}

	public void setDescartado(Boolean descartado) {
		this.descartado = descartado;
	}

	public Boolean getIgnorado() {
		return ignorado;
	}

	public void setIgnorado(Boolean ignorado) {
		this.ignorado = ignorado;
	}

	@Override
	public String toString() {
		return "ClassificacaoFADTO [id=" + id + ", confirmado=" + confirmado + ", descartado=" + descartado
				+ ", ignorado=" + ignorado + "]";
	}

}
