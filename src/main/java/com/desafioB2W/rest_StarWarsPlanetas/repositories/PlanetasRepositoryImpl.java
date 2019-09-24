package com.desafioB2W.rest_StarWarsPlanetas.repositories;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PlanetasRepositoryImpl implements PlanetasRepositoryCustomInterface {
	
	@Autowired
    private MongoTemplate mongoTemplate;
    private Collation ignoreCaseCollation;
    
    public PlanetasRepositoryImpl() {
        this.ignoreCaseCollation = Collation.of(Locale.ENGLISH).strength(Collation.ComparisonLevel.secondary());
    }

	@Override
	public Planeta findByNomeIgnoreCase(String nome) {
		Query query = new Query(Criteria.where("nome").is(nome));
        query.collation(this.ignoreCaseCollation);

        return mongoTemplate.findOne(query, Planeta.class);
	}

}
