package com.desafioB2W.rest_StarWarsPlanetas.service;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafioB2W.rest_StarWarsPlanetas.models.SWAPIPlaneta;
import com.desafioB2W.rest_StarWarsPlanetas.service.utils.SWAPIPlanetaSearch;

@Service
public class SWAPIPlanetaService {
	public static final String SWAPI_BASE_URL = "https://swapi.co";
    public static final String SWAPI_PLANETS_RESOURCE = "/api/planets/";
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<SWAPIPlaneta> getSwapiPlaneta(String nome) throws URISyntaxException{
    	URIBuilder searchURL = new URIBuilder(SWAPIPlanetaService.SWAPI_BASE_URL)
    			.setPath(SWAPIPlanetaService.SWAPI_PLANETS_RESOURCE)
    			.addParameter("search", nome)
    			.setCharset(Charset.forName("UTF-8"));
    	
    	SWAPIPlanetaSearch resultados = restTemplate.getForObject(searchURL.build(), SWAPIPlanetaSearch.class);
    	
    	if(resultados == null || resultados.getResults() == null) {
    		return new ArrayList<>();
    	}
    	
    	return Arrays.stream(resultados.getResults()).collect(Collectors.toList());
        
    }

}
