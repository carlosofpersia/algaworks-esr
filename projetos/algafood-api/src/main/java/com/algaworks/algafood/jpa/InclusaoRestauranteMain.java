package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {
	
	//aplicação spring não web;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Restaurante Brasileiro -> Incluido");
		restaurante1.setTaxaFrete(new BigDecimal("8.20"));

		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Restaurante Japoneza -> Incluido");
		restaurante2.setTaxaFrete(new BigDecimal("7.20"));

		restaurante1 = restauranteRepository.salvar(restaurante1);
		restaurante2 = restauranteRepository.salvar(restaurante2);

		System.out.println("");
		System.out.printf(" %d) %s -> R$ %f", restaurante1.getId(), restaurante1.getNome(), restaurante1.getTaxaFrete());
		System.out.println("");
		System.out.printf(" %d) %s -> R$ %f", restaurante2.getId(), restaurante2.getNome(), restaurante2.getTaxaFrete());
		System.out.println("");

	}
}
