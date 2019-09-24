package com.desafioB2W.rest_StarWarsPlanetas.controllerTest;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.desafioB2W.rest_StarWarsPlanetas.controllers.PlanetasController;
import com.desafioB2W.rest_StarWarsPlanetas.exceptions.PlanetNotFoundException;
import com.desafioB2W.rest_StarWarsPlanetas.exceptions.PlanetaJaExisteException;
import com.desafioB2W.rest_StarWarsPlanetas.models.Planeta;
import com.desafioB2W.rest_StarWarsPlanetas.models.SWAPIPlaneta;
import com.desafioB2W.rest_StarWarsPlanetas.repositories.PlanetasRepository;
import com.desafioB2W.rest_StarWarsPlanetas.service.SWAPIPlanetaService;

@RunWith(MockitoJUnitRunner.class)
public class PlanetasControllerTest {
	
	@Mock
	private PlanetasRepository mockRepository;
	
	@Mock
	private SWAPIPlanetaService mockSwapiService;
	
	@InjectMocks
	private PlanetasController planetasControl;
	
	private List<SWAPIPlaneta> busca;
	
	@Before
	public void setUp() {
		SWAPIPlaneta swp1 = new SWAPIPlaneta();
		swp1.setName("Dagobah");
		String[] dagobahFilmes = {"2","3","6"};
		swp1.setFilms(dagobahFilmes);
		
		SWAPIPlaneta swp2 = new SWAPIPlaneta();
		swp2.setName("Hoth");
		String[] hothFilmes = {"2"};
		swp2.setFilms(hothFilmes);
		
		SWAPIPlaneta swp3 = new SWAPIPlaneta();
		swp3.setName("Alderaan");
		String[] alderaanFilmes = {"6","1"};
		swp3.setFilms(alderaanFilmes);
		
		busca = new ArrayList<>();
		
		busca.add(swp1);
		busca.add(swp2);
		busca.add(swp3);
		
	}
	
	@Test
	public void testAdicionarPlaneta() throws Exception {
		Planeta p = new Planeta(ObjectId.get(), "Yavin IV", "Temperado", "florestas", 3);
		
		//Planeta nao existe ainda, verificacao por nome retorna null
				
		when(mockSwapiService.getSwapiPlaneta(anyString())).thenReturn(busca);
		when(mockRepository.findByNomeIgnoreCase(anyString())).thenReturn(null);
		
		planetasControl.createPlaneta(p);
		
		verify(mockRepository, atLeastOnce()).findByNomeIgnoreCase(p.getNome());
        verify(mockSwapiService, times(1)).getSwapiPlaneta(p.getNome());
        verify(mockRepository, times(1)).save(p);
        
        //limpando repositorio
        reset(mockRepository, mockSwapiService);
              
        //verificacao por nome retorna um planeta
        when(mockRepository.findByNomeIgnoreCase(anyString())).thenReturn(p);
          
        //Lanca excecao ao tentar inserir um planeta que ja existe
        assertThatExceptionOfType(PlanetaJaExisteException.class).isThrownBy(
                ()->planetasControl.createPlaneta(p)).withMessageContaining(p.getNome());
        
        verify(mockRepository, atLeastOnce()).findByNomeIgnoreCase(p.getNome());
        verify(mockSwapiService, never()).getSwapiPlaneta(p.getNome());
        verify(mockRepository, never()).save(any(Planeta.class));
        
        //tentar inserir um planeta que nao existe no SWAPI lanca excecao
        reset(mockRepository, mockSwapiService);
        
        when(mockSwapiService.getSwapiPlaneta(anyString())).thenReturn(null);
        when(mockRepository.findByNomeIgnoreCase(anyString())).thenReturn(null);
        
        assertThatExceptionOfType(PlanetNotFoundException.class).isThrownBy(
                ()->planetasControl.createPlaneta(p)).withMessageContaining(p.getNome());
        
        //retorna vazio
        reset(mockRepository, mockSwapiService);
        busca.clear();
        
        when(mockSwapiService.getSwapiPlaneta(anyString())).thenReturn(busca);
        when(mockRepository.findByNomeIgnoreCase(anyString())).thenReturn(null);
        
        assertThatExceptionOfType(PlanetNotFoundException.class).isThrownBy(
                ()->planetasControl.createPlaneta(p)).withMessageContaining(p.getNome());
        
	}

}
