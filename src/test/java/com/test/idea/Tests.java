package com.test.idea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.idea.model.entity.Anuncio;
import com.test.idea.model.entity.Chalet;
import com.test.idea.model.entity.Foto;
import com.test.idea.model.entity.Garaje;
import com.test.idea.model.entity.Piso;
import com.test.idea.model.entity.Quality;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

	@Test
	public void test() {
		List<Anuncio> listaAnuncios = getDatos();
		
		//Calcular la puntuación de todos los anuncios
		System.out.println("-- PUNTUACIÓN DE LOS ANUNCIOS --");
		Prueba.calculaPuntuacion(listaAnuncios);
		for (Anuncio anuncio : listaAnuncios) {
			System.out.println(anuncio.toStringPuntuacion());
		}
		
		//Listar los anuncios para un usuario
		System.out.println("-- ANUNCIOS USUARIO --");
		for (Anuncio anuncio : Prueba.getListadoUsuario(listaAnuncios)) {
			System.out.println(anuncio.toStringUsuario());
		}
		
		//listar los anuncios para un responsable de calidad
		System.out.println("-- ANUNCIOS RESPONSABLE CALIDAD --");
		for (Anuncio anuncio : Prueba.getListadoResponsableCalidad(listaAnuncios)) {
			System.out.println(anuncio.toStringResponsableCalidad());
		}
	}
	
	public List<Anuncio> getDatos () {
		Map<Integer, Foto> mapaFotos = new HashMap<Integer, Foto>();
		mapaFotos.put(1, new Foto(1, "http://www.idealista.com/pictures/1", Quality.SD));
		mapaFotos.put(2, new Foto(2, "http://www.idealista.com/pictures/2", Quality.HD));
		mapaFotos.put(3, new Foto(3, "http://www.idealista.com/pictures/3", Quality.SD));
		mapaFotos.put(4, new Foto(4, "http://www.idealista.com/pictures/4", Quality.HD));
		mapaFotos.put(5, new Foto(5, "http://www.idealista.com/pictures/5", Quality.SD));
		mapaFotos.put(6, new Foto(6, "http://www.idealista.com/pictures/6", Quality.SD));
		mapaFotos.put(7, new Foto(7, "http://www.idealista.com/pictures/7", Quality.SD));
		
		List<Anuncio> listaAnuncios = new ArrayList<Anuncio>();		
		
		int id = 1;
		listaAnuncios.add(new Chalet(id++, "Este piso es una ganga, compra, compra, COMPRA!!!!!", 300, null));
		listaAnuncios.add(new Piso(id++, "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", null, mapaFotos.get(4)));
		listaAnuncios.add(new Chalet(id++, "", 210, 25, mapaFotos.get(2)));
		listaAnuncios.add(new Piso(id++, "Ático céntrico muy luminoso y recién reformado, parece nuevo", 130, mapaFotos.get(5)));
		listaAnuncios.add(new Piso(id++, "Pisazo", null, mapaFotos.get(3), mapaFotos.get(4)));
		listaAnuncios.add(new Garaje(id++, "", mapaFotos.get(6)));
		listaAnuncios.add(new Garaje(id++, "Garaje en el centro de Albacete"));
		listaAnuncios.add(new Chalet(id++, "Maravilloso chalet situado en als afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", 150, 20, mapaFotos.get(1), mapaFotos.get(7)));
		
		return listaAnuncios;
	}
	
}

