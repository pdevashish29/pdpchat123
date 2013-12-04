package cz.jiripinkas.example.chat.service;

import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.example.chat.entity.Role;
import cz.jiripinkas.example.chat.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
@Transactional
public class RoleServiceIT {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RoleService roleService;

	@Before
	public void setUp() throws Exception {
		{
			Role role = new Role();
			role.setName("ROLE_USER");
			entityManager.persist(role);
		}
		{
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			entityManager.persist(role);
		}
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(2, roleService.findAll().size());
		
		List<Role> findAll = roleService.findAll();
		for (Role role : findAll) {
			System.out.println(role.getId());
		}
	}

	@Ignore
	@Test
	public void testFindOne() {
		Assert.assertEquals("ROLE_USER", roleService.findOne(1).getName());
	}

	@Test
	public void testFindByName() {
		Assert.assertEquals("ROLE_USER", roleService.findByName("ROLE_USER").getName());
	}

	@Test
	public void testCount() {
		Assert.assertEquals(2, roleService.count());
	}

}
