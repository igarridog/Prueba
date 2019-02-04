package com.test.idea.model.comparator;

import java.util.Comparator;

import com.test.idea.model.entity.Anuncio;

public class ComparatorPuntuacion implements Comparator<Anuncio> {

	public int compare(Anuncio obj1, Anuncio obj2) {
		if (obj1 != null && obj2 != null) {
			if (obj1.getPuntuacion() == obj2.getPuntuacion()) {
				return 0;
			}
			
			return obj1.getPuntuacion() > obj2.getPuntuacion() ? -1 : 1;
		}
		
		return 0;
	}
}