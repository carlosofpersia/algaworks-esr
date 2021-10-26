package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {
	
	//aplicação spring não web;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante = new Restaurante();
		restaurante.setId(Long.valueOf(1));
		restaurante.setNome("Restaurante do Batata -> Alteração");
		restaurante.setTaxaFrete(new BigDecimal("5.88"));

		restaurante = restauranteRepository.salvar(restaurante);

		System.out.println("");
		System.out.printf(" %d) %s -> %f", restaurante.getId(), restaurante.getNome(), restaurante.getTaxaFrete());
		System.out.println("");


	}

}
