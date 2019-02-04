package com.test.idea.model.entity;

public class Piso extends Vivienda {
	
	public Piso(Integer id, String description, Integer houseSize, Foto... fotos) {
		super(id, description, Typology.FLAT, houseSize, fotos);
	}
	
	public void calculaPuntuacion() {
		super.calculaPuntuacion();
		
		//aporta 10 puntos si la descripcion tiene 20 o mas palabras y 30 puntos si tiene 50 o mas palabras
		if (this.getDescription() != null && !this.getDescription().isEmpty()) {
			if (this.getNumeroPalabras() >= 50) {
				super.puntuacion += 30;
			} else if (this.getNumeroPalabras() >= 20) {
				super.puntuacion += 10;
			}
		}
		
		//Que el anuncio este completo tambien aporta puntos
		if (this.isCompleto()) {
			super.puntuacion += 40;
		}
		
		super.validaRelevancia();
		super.puntuacion = (super.puntuacion < 0 ? 0 : (super.puntuacion > 100 ? 100 : super.puntuacion));
	}
}