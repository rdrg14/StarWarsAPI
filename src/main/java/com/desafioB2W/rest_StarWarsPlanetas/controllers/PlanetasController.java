package com.desafioB2W.rest_StarWarsPlanetas.controllers;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioB2W.rest_StarWarsPlanetas.exceptions.PlanetNotFoundException;
import com.desafioB2W.rest_StarWarsPlanetas.exceptions.PlanetaJaExisteException;
import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;
import com.desafioB2W.rest_StarWarsPlanetas.models.SWAPIPlaneta;
import com.desafioB2W.rest_StarWarsPlanetas.repositories.PlanetasRepository;
import com.desafioB2W.rest_StarWarsPlanetas.service.SWAPIPlanetaService;


@RestController
@RequestMapping("/planetas")
public class PlanetasController {
	
	@Autowired
	private PlanetasRepository repository;
	@Autowired
    private SWAPIPlanetaService swApiPlanetaService;
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public List<Planeta> getAllPlanetas(){
		return repository.findAll();
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public Planeta getPlanetaById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Planeta getPlanetaByNome(@RequestParam(value="nome") String nome) {
		return repository.findByNomeIgnoreCase(nome);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Planeta createPlaneta(@Valid @RequestBody Planeta planeta) throws URISyntaxException, PlanetNotFoundException, PlanetaJaExisteException {
		if(repository.findByNomeIgnoreCase(planeta.getNome()) != null) {
			PlanetaJaExisteException ex = new PlanetaJaExisteException();
			ex.setId(planeta.getNome());
			throw ex;
		}
		
		planeta.set_id(ObjectId.get());
		planeta.setFilmes(quantidadeFilmesPorNome(planeta.getNome()));
		repository.save(planeta);
		return planeta;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePlaneta(@PathVariable("id") ObjectId id) {
		repository.delete(repository.findBy_id(id));;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deletePlaneta(@RequestParam(value="nome") String nome) {
		repository.delete(repository.findByNomeIgnoreCase(nome));;
	}
	
	private Integer quantidadeFilmesPorNome(String nome) throws URISyntaxException, PlanetNotFoundException {
        List<SWAPIPlaneta> swAPIPlanetas = swApiPlanetaService.getSwapiPlaneta(nome);
        if (swAPIPlanetas==null || swAPIPlanetas.isEmpty()) {
            PlanetNotFoundException ex = new PlanetNotFoundException();
            ex.setId(nome);
            throw ex;
        }

        return swAPIPlanetas.stream()
            .filter(p -> p.getName().equalsIgnoreCase(nome))
            .map(p->{
                if(p.getFilms() == null)
                    return 0;
                return p.getFilms().length;
            }).mapToInt(Integer::intValue)
            .sum();
        
    }

}
