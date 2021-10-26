package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositorioImpl implements FormaPagamentoRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<FormaPagamento> listar() {
		TypedQuery<FormaPagamento> query = this.entityManager.createQuery(" from FormaPagamento ", FormaPagamento.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public FormaPagamento buscar(Long id) {
		return entityManager.find(FormaPagamento.class, id);
	}

	@Override
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return entityManager.merge(formaPagamento);
	}

	@Override
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = buscar(formaPagamento.getId());
		entityManager.remove(formaPagamento);
	}

	
}
