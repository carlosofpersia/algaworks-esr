package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {
	
	//aplicação spring não web;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

		List<Cidade> todasCidades = cidadeRepository.listar();
		System.out.println("");
		for (Cidade cidade : todasCidades) {
			System.out.printf("Cidade: %s - Estado: %s\n", cidade.getNome(), cidade.getEstado().getNome());
		}
	}

}
