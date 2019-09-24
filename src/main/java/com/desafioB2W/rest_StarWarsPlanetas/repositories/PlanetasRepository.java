package com.desafioB2W.rest_StarWarsPlanetas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;

public interface PlanetasRepository extends MongoRepository<Planeta,String>, PlanetasRepositoryCustomInterface{
	Planeta findBy_id(ObjectId _id);

}
