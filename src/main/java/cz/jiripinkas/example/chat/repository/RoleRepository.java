package cz.jiripinkas.example.chat.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.jiripinkas.example.chat.entity.Role;

@Repository
public class RoleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Role> findAll() {
		return entityManager.createNamedQuery(Role.FIND_ALL, Role.class)
				.getResultList();
	}

	public Role findOne(int id) {
		return entityManager.find(Role.class, id);
	}

	public Role findByName(String name) {
		return entityManager.createNamedQuery(Role.FIND_BY_NAME, Role.class)
				.setParameter("name", name).getSingleResult();
	}
	
//	public List<Role> findByRoleName() {
//	}
	
	public long count() {
		return entityManager.createNamedQuery(Role.COUNT, Long.class).getSingleResult();
	}
}
