package com.desafioB2W.rest_StarWarsPlanetas.service.utils;

import com.desafioB2W.rest_StarWarsPlanetas.models.SWAPIPlaneta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Data;

/**
 * Classe utilitária para buscar planetas pela API
 * https://swapi.co/api
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SWAPIPlanetaSearch {
    
    private SWAPIPlaneta[] results;

	public SWAPIPlaneta[] getResults() {
		return results;
	}

   
}