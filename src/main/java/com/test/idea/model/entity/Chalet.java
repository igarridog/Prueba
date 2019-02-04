package com.test.idea.model.entity;

public class Chalet extends Vivienda {
	
	private Integer gardenSize;
	
	public Chalet(Integer id, String description, Integer houseSize, Integer gardenSize, Foto... fotos) {
		super(id, description, Typology.CHALET, houseSize, fotos);
		this.gardenSize = gardenSize;
	}
	
	public Integer getGardenSize() {
		return gardenSize;
	}
	public void setGardenSize(Integer gardenSize) {
		this.gardenSize = gardenSize;
	}
	
	/**
	 * @return true si cumplen los requisitos de la vivienda y tambien tamanyo del jardin
	 */
	public boolean isCompleto() {
		return super.isCompleto() && gardenSize != null;
	}
	
	public void calculaPuntuacion() {
		super.calculaPuntuacion();
		
		//si tiene mas de 50 palabras, anyade 20 puntos
		if (this.getDescription() != null && !this.getDescription().isEmpty()) {
			if (this.getNumeroPalabras() >= 50) {
				super.puntuacion += 20;
			}
		}
		
		//Que el anuncio este completo tambien aporta puntos
		if (this.isCompleto()) {
			super.puntuacion += 40;
		}
		
		super.validaRelevancia();
		super.puntuacion = (super.puntuacion < 0 ? 0 : (super.puntuacion > 100 ? 100 : super.puntuacion));
	}

	@Override
	public String toString() {
		return super.toString() + ", gardenSize=" + gardenSize;
	}
	
	public String toStringUsuario() {
		String texto = super.toStringUsuario();
		if (gardenSize != null) {
			texto += ", gardenSize=" + gardenSize;
		}
		return texto;
	}
}