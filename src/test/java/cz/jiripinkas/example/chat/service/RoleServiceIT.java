package cz.jiripinkas.example.chat.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.example.chat.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
@Transactional
public class RoleServiceIT {

	@Autowired
	private RoleService roleService;

	@Test
	public void testFindAll() {
		Assert.assertEquals(2, roleService.findAll().size());
	}

	@Test
	public void testFindOne() {
		Assert.assertEquals("ROLE_USER", roleService.findOne(1).getName());
	}

	@Test
	public void testFindByName() {
		Assert.assertEquals("ROLE_USER", roleService.findByName("ROLE_USER")
				.getName());
	}

	@Test
	public void testCount() {
		Assert.assertEquals(2, roleService.count());
	}

	@Test
	public void testFindByUserName() {
		List<Role> list = roleService.findByUserName("admin");
		Assert.assertEquals(2, list.size());
		Assert.assertEquals("ROLE_ADMIN", list.get(0).getName());
		Assert.assertEquals("ROLE_USER", list.get(1).getName());
	}

}
