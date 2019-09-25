package com.desafioB2W.rest_StarWarsPlanetas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SWAPIPlaneta {
	private String name;
    private String[] films;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getFilms() {
		return films;
	}
	public void setFilms(String[] films) {
		this.films = films;
	}
}
