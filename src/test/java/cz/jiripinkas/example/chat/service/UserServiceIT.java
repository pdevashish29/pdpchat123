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

import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
@Transactional
public class UserServiceIT {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		{
			User user = new User();
			user.setName("admin");
			entityManager.persist(user);
		}
		{
			User user = new User();
			user.setName("guest");
			entityManager.persist(user);
		}
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(2, userService.findAll().size());
		
		List<User> findAll = userService.findAll();
		for (User user : findAll) {
			System.out.println(user.getId());
		}
	}

	@Ignore
	@Test
	public void testFindOne() {
		Assert.assertEquals("admin", userService.findOne(1).getName());
	}

	@Test
	public void testFindByName() {
		Assert.assertEquals("admin", userService.findByName("admin").getName());
	}

	@Test
	public void testCount() {
		Assert.assertEquals(2L, userService.count());
	}

}
