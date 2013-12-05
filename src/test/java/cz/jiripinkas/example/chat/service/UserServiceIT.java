package cz.jiripinkas.example.chat.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.example.chat.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
@Transactional
public class UserServiceIT {

	@Autowired
	private UserService userService;

	@Test
	public void testFindAll() {
		Assert.assertEquals(2, userService.findAll().size());
	}

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

	@Test
	public void testFindByUserName() {
		List<User> list = userService.findByRoleName("ROLE_ADMIN");
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("admin", list.get(0).getName());
	}

}
