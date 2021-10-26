package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamentoMain {
	
	//aplicação spring não web;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

		List<FormaPagamento> todasFormasPagamento = formaPagamentoRepository.listar();
		System.out.println("");
		for (FormaPagamento formaPagamento : todasFormasPagamento) {
			System.out.printf("%d - %s\n", formaPagamento.getId(), formaPagamento.getDescricao());
		}
	}

}
