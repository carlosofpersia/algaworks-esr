package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositorioImpl implements CidadeRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Cidade> listar() {
		TypedQuery<Cidade> query = this.entityManager.createQuery(" from Cidade ", Cidade.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return entityManager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return entityManager.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Cidade cidade) {
		cidade = buscar(cidade.getId());
		entityManager.remove(cidade);
	}

	
}
