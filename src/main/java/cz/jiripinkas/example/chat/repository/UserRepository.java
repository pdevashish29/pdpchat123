package cz.jiripinkas.example.chat.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.jiripinkas.example.chat.entity.User;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findAll() {
		return entityManager.createNamedQuery(User.FIND_ALL, User.class)
				.getResultList();
	}

	public User findOne(int id) {
		return entityManager.find(User.class, id);
	}

	public User findByName(String name) {
		return entityManager.createNamedQuery(User.FIND_BY_NAME, User.class)
				.setParameter("name", name).getSingleResult();
	}
	
//	public List<User> findByRoleName() {
//	}
	
	public long count() {
		return entityManager.createNamedQuery(User.COUNT, Long.class).getSingleResult();
	}
}
