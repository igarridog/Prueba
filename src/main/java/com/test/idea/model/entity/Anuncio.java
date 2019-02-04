package com.test.idea.model.entity;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Anuncio {
	
	public enum Typology { CHALET, FLAT, GARAGE };
	private static String[] PALABRAS_PLUS = { "luminoso", "nuevo", "centrico", "reformado", "atico" };
	private static int PUNTUACION_IRRELEVANTE = 40;
	
	private Integer id;
	private String description;
	private Typology typology;
	private List<Foto> pictures;
	
	protected Integer puntuacion;
	private Date fechaIrrelevante;
	
	public Anuncio(Integer id, String description, Typology typology, Foto... fotos) {
		super();
		this.id = id;
		this.description = description;
		this.typology = typology;
		if (fotos != null) {
			this.pictures = new ArrayList<Foto>();
			for (Foto foto : fotos) {
				this.pictures.add(foto);
			}
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Typology getTypology() {
		return typology;
	}
	public void setTypology(Typology typology) {
		this.typology = typology;
	}
	public List<Foto> getPictures() {
		return pictures;
	}
	public void setPictures(List<Foto> pictures) {
		this.pictures = pictures;
	}
	public Date getFechaIrrelevante() {
		return fechaIrrelevante;
	}
	public void setFechaIrrelevante(Date fechaIrrelevante) {
		this.fechaIrrelevante = fechaIrrelevante;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	/**
	 * @return el texto de la descripcion en minusculas y sin tildes
	 */
	private String empobrecerDescription() {
		if (description != null && !description.isEmpty()) {
			String texto = Normalizer.normalize(description.toLowerCase(), Normalizer.Form.NFD);
			return texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		}
		return description;
	}
	
	/**
	 * @return numero de palabras en la descripcion
	 */
	public int getNumeroPalabras() {
		if (description != null && !description.isEmpty()) {
			String[] palabras = this.empobrecerDescription().split("\\W+");
			return palabras.length;
		}
		return 0;
	}

	/**
	 * @return true si tiene descripcion y al menos una foto. los garajes no es necesario que el anuncio tenga descripcion
	 */
	public boolean isCompleto() {
		if (CollectionUtils.isEmpty(this.pictures)) {
			return false;
		}

		if (typology == Typology.GARAGE) {
			return true;
		}
		
		if (description != null && !description.isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * @return la puntuacion del anuncio segun los criterios
	 */
	public void calculaPuntuacion() {
		puntuacion = 0;
		
		if (CollectionUtils.isEmpty(this.pictures)) {
			//Que el anuncio no tenga ninguna foto resta 10 puntos
			puntuacion -= 10;
		} else {
			//Cada foto que tenga el anuncio proporciona 20 puntos si es una foto HD o 10 si no lo es
			for (Foto foto : pictures) {
				puntuacion += foto.getQuality().getPuntuacion();
			}
		}
		
		//Que tenga descripcion aporta 5 puntos
		if (description != null && !description.isEmpty()) {
			puntuacion += 5;
			
			//Que las siguientes palabras aparezcan en la descripcion anyaden 5 puntos cada una: Luminoso, Nuevo, Centrico, Reformado, Atico
			String[] palabras = this.empobrecerDescription().split("\\W+");
			for (String palabra : palabras) {
				if (Arrays.asList(PALABRAS_PLUS).contains(palabra)) {
					puntuacion += 5;
				}
			}
		}
	}
	
	/**
	 * Un anuncio se considera irrelevante si tiene una puntuacion menor a PUNTUACION_IRRELEVANTE
	 */
	public boolean isIrrelevante() {
		return this.getPuntuacion() < PUNTUACION_IRRELEVANTE;
	}
	
	/**
	 * Si un anuncio ya es relevante borra la posible fecha
	 * Si un anuncio es irrelevante pero tiene una fecha no la toca, solo la anyade si no tiene fecha
	 */
	protected void validaRelevancia() {
		if (!this.isIrrelevante()) {
			fechaIrrelevante = null;
		} else if (fechaIrrelevante == null) {
			fechaIrrelevante = new Date();
		}
	}

	@Override
	public String toString() {
		return "id=" + id + ", puntuacion=" + this.getPuntuacion() + ", fechaIrrelevante=" + this.getFechaIrrelevante() + ", completo=" 
				+ (this.isCompleto()?"SI":"NO") + ", description=" + description + " (" + this.getNumeroPalabras() + "), typology=" 
				+ typology + ", pictures=" + pictures;
	}
	
	public String toStringPuntuacion() {
		return "id=" + id + ", puntuacion=" + this.getPuntuacion();
	}
	
	public String toStringUsuario() {
		String texto = "typology=" + typology;
		texto += description != null && !description.isEmpty() ? ", description=" + description : "";
		if (!CollectionUtils.isEmpty(pictures)) {
			texto += ", pictures=";
			for (Foto foto : pictures) {
				texto += "[" + foto.getUrl() + "] ";	
			}
		}
		return texto;
	}
	
	public String toStringResponsableCalidad() {
		return "id=" + id + ", puntuacion=" + puntuacion + ", fechaIrrelevante=" + fechaIrrelevante;
	}
	
}