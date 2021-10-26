package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositorioImpl implements PermissaoRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Permissao> listar() {
		TypedQuery<Permissao> query = this.entityManager.createQuery(" from Permissao ", Permissao.class);
		// query.setParameter(0, query);
		return query.getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return entityManager.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return entityManager.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		entityManager.remove(permissao);
	}

	
}
