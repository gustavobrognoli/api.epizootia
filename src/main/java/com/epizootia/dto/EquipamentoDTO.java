package com.epizootia.dto;

public class EquipamentoDTO {

	private int id;
	private boolean puca;
	private boolean castro;
	private boolean shanonn;
	private boolean cdc;

	public EquipamentoDTO() {
		// TODO Auto-generated constructor idadeAnimal
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPuca() {
		return puca;
	}

	public void setPuca(boolean puca) {
		this.puca = puca;
	}

	public boolean isCastro() {
		return castro;
	}

	public void setCastro(boolean castro) {
		this.castro = castro;
	}

	public boolean isShanonn() {
		return shanonn;
	}

	public void setShanonn(boolean shanonn) {
		this.shanonn = shanonn;
	}

	public boolean isCdc() {
		return cdc;
	}

	public void setCdc(boolean cdc) {
		this.cdc = cdc;
	}

	@Override
	public String toString() {
		return "EquipamentoDTO [id=" + id + ", puca=" + puca + ", castro=" + castro + ", shanonn=" + shanonn + ", cdc="
				+ cdc + "]";
	}

}
