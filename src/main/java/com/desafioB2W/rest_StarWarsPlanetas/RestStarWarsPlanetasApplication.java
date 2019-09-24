package com.desafioB2W.rest_StarWarsPlanetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestStarWarsPlanetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestStarWarsPlanetasApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
