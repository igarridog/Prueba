package com.test.idea.model.entity;

public class Garaje extends Anuncio {
	
	public Garaje(Integer id, String description, Foto... fotos) {
		super(id, description, Typology.GARAGE, fotos);
	}
	
	public void calculaPuntuacion() {
		super.calculaPuntuacion();
		
		//Que el anuncio este completo tambien aporta puntos
		if (this.isCompleto()) {
			super.puntuacion += 40;
		}

		super.validaRelevancia();
		super.puntuacion = (super.puntuacion < 0 ? 0 : (super.puntuacion > 100 ? 100 : super.puntuacion));
	}
}