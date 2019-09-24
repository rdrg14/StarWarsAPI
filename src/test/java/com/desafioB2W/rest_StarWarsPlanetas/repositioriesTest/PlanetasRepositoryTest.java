package com.desafioB2W.rest_StarWarsPlanetas.repositioriesTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafioB2W.rest_StarWarsPlanetas.RestStarWarsPlanetasApplication;
import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;
import com.desafioB2W.rest_StarWarsPlanetas.repositories.PlanetasRepository;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestStarWarsPlanetasApplication.class)
public class PlanetasRepositoryTest {
	
	@Autowired
	private PlanetasRepository repository;
	
	@Before
	public void setUp() throws Exception {
		Planeta planeta1 = new Planeta();
		Planeta planeta2 = new Planeta();
		Planeta planeta3 = new Planeta();
		
		planeta1.setNome("Tatooine");
		planeta1.setClima("Arido");
		planeta1.setTerreno("Deserto");
		
		planeta2.setNome("Dagobah");
		planeta2.setClima("Tropical");
		planeta2.setTerreno("Pantano");
		
		planeta3.setNome("endor");
		planeta3.setClima("Temperado");
		planeta3.setTerreno("Floresta");
		
		this.repository.save(planeta1);
		this.repository.save(planeta2);
		this.repository.save(planeta3);
		
		assertNotNull(planeta1.get_id());
		assertNotNull(planeta2.get_id());
		assertNotNull(planeta3.get_id());
	}
	
	@Test
	public void testBuscarDados() {
		Planeta p1 = this.repository.findByNomeIgnoreCase("dagobah");
		String id = p1.get_id();
		
		assertNotNull(p1);
		assertNotNull(id);
		assertEquals("Pantano", p1.getTerreno());
		assertNotNull(this.repository.findById(id));
		
		List<Planeta> planetas = this.repository.findAll();
		
		assertEquals(3, planetas.size());
			
	}
	
	@Test
	public void testRemoverPlaneta() {
				
		Planeta endor = this.repository.findByNomeIgnoreCase("Endor");
		
		assertNotNull(endor);
		
		this.repository.deleteById(endor.get_id());
		
		this.repository.delete(this.repository.findByNomeIgnoreCase("Tatooine"));
		
		assertNull(this.repository.findByNomeIgnoreCase("Endor"));
		assertNull(this.repository.findByNomeIgnoreCase("Tatooine"));
	}
	
	
	@After
	public void tearDown() throws Exception{
		this.repository.deleteAll();
	}


}
