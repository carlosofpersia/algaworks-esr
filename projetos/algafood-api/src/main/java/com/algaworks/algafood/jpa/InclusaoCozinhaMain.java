package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {
	
	//aplicação spring não web;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinha = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		cozinha1.setTwitter("#cozinhabrasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japoneza");
		cozinha2.setTwitter("#cozinhajaponeza");

		cozinha1 = cozinha.salvar(cozinha1);
		cozinha2 = cozinha.salvar(cozinha2);

		System.out.println("");
		System.out.printf(" %d) %s -> %s", cozinha1.getId(), cozinha1.getNome(), cozinha1.getTwitter());
		System.out.println("");
		System.out.printf(" %d) %s -> %s", cozinha2.getId(), cozinha2.getNome(), cozinha2.getTwitter());
		System.out.println("");

	}

}
