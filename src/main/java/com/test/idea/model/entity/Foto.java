package com.test.idea.model.entity;

public class Foto {

	private Integer id;
	private String url;
	private Quality quality;
	
	public Foto(Integer id, String url, Quality quality) {
		super();
		this.id = id;
		this.url = url;
		this.quality = quality;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "id=" + id + ", url=" + url + ", quality=" + quality;
	}
	
}