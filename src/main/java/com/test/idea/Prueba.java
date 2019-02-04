package com.test.idea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.idea.model.comparator.ComparatorPuntuacion;
import com.test.idea.model.entity.Anuncio;

public class Prueba {

	public static void calculaPuntuacion(List<Anuncio> listaAnuncios) {
		for (Anuncio anuncio : listaAnuncios) {
			anuncio.calculaPuntuacion();
		}
	}
	
	public static List<Anuncio> getListadoUsuario(List<Anuncio> listaAnuncios) {
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		
		for (Anuncio anuncio : listaAnuncios) {
			if (!anuncio.isIrrelevante()) {
				anuncios.add(anuncio);
			}
		}
		
		Collections.sort(anuncios, new ComparatorPuntuacion());
		
		return anuncios;
	}
	
	public static List<Anuncio> getListadoResponsableCalidad(List<Anuncio> listaAnuncios) {
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		
		for (Anuncio anuncio : listaAnuncios) {
			if (anuncio.isIrrelevante()) {
				anuncios.add(anuncio);
			}
		}
		return anuncios;
	}

}
 