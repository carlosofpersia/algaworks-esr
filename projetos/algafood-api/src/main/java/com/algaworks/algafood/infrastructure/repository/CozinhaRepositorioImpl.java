package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositorioImpl implements CozinhaRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Cozinha> listar() {
		TypedQuery<Cozinha> query = this.entityManager.createQuery(" from Cozinha ", Cozinha.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return entityManager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		entityManager.remove(cozinha);
	}

	
}
