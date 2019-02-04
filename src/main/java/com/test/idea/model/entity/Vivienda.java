package com.test.idea.model.entity;

public class Vivienda extends Anuncio {
	
	private Integer houseSize;

	public Vivienda(Integer id, String description, Typology typology, Integer houseSize, Foto... fotos) {
		super(id, description, typology, fotos);
		this.houseSize = houseSize;
	}
	
	public Integer getHouseSize() {
		return houseSize;
	}
	public void setHouseSize(Integer houseSize) {
		this.houseSize = houseSize;
	}

	/**
	 * @return true si cumplen los requisitos del anuncio y tambien tamanyo de vivienda
	 */
	public boolean isCompleto() {
		return super.isCompleto() && houseSize != null;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", houseSize=" + houseSize;
	}
	
	public String toStringUsuario() {
		String texto = super.toStringUsuario();
		if (houseSize != null) {
			texto += ", houseSize=" + houseSize;
		}
		return texto;
	}
}