package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositorioImpl implements RestauranteRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Restaurante> listar() {

		TypedQuery<Restaurante> query = this.entityManager.createQuery(" from Restaurante ", Restaurante.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return this.entityManager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return this.entityManager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		Restaurante restauranteManeged = this.buscar(restaurante.getId());
		this.entityManager.remove(restauranteManeged);
	}
}
