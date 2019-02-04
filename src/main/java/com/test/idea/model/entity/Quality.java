package com.test.idea.model.entity;

public enum Quality {

	HD ("HD",	20),
	SD ("SD",	10);
	
	private String tipo;
	private Integer puntuacion;
	
	private Quality(String tipo, Integer puntuacion) {
		this.tipo = tipo;
		this.puntuacion = puntuacion;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}