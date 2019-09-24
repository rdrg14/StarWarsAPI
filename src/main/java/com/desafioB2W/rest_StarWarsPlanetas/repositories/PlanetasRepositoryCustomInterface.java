package com.desafioB2W.rest_StarWarsPlanetas.repositories;

import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;

public interface PlanetasRepositoryCustomInterface {
	
	public Planeta findByNomeIgnoreCase(String nome);

}
