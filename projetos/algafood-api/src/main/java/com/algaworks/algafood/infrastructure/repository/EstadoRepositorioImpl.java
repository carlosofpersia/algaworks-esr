package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositorioImpl implements EstadoRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Estado> listar() {

		TypedQuery<Estado> query = this.entityManager.createQuery(" from Estado ", Estado.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return this.entityManager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return this.entityManager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		Estado restauranteManeged = this.buscar(estado.getId());
		this.entityManager.remove(restauranteManeged);
	}
}
