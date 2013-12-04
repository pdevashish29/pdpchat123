package cz.jiripinkas.example.chat.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cz.jiripinkas.example.chat.annotation.TransactionalRW;
import cz.jiripinkas.example.chat.entity.Chatroom;
import cz.jiripinkas.example.chat.entity.Role;
import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.entity.UserRole;

@Service
public class InitDbService {

	@Autowired
	private ChatroomService chatroomService;

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionalRW
	@PostConstruct
	// initialize database each day
	@Scheduled(fixedDelay = 86400000, initialDelay = 86400000)
	public void init() {
		System.out.println("*** START INIT DATABASE ***");
		chatroomService.deleteAll();
		{
			User userAdmin = new User();
			userAdmin.setName("admin");
			entityManager.persist(userAdmin);

			User userGuest = new User();
			userGuest.setName("guest");
			entityManager.persist(userGuest);

			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			entityManager.persist(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			entityManager.persist(roleAdmin);

			UserRole userRoleAdmin = new UserRole();
			userRoleAdmin.setRole(roleAdmin);
			userRoleAdmin.setUser(userAdmin);
			entityManager.persist(userRoleAdmin);

			UserRole userRoleUser = new UserRole();
			userRoleUser.setRole(roleUser);
			userRoleUser.setUser(userGuest);
			entityManager.persist(userRoleUser);

		}
		{
			Chatroom chatroom = new Chatroom();
			chatroom.setName("Soccer");
			chatroom.setDescription("The most popular game on Earth!");
			chatroomService.save(chatroom);
		}

		{
			Chatroom chatroom = new Chatroom();
			chatroom.setName("Programmers");
			chatroom.setDescription("How to code like a pro :)");
			chatroomService.save(chatroom);
		}

		{
			Chatroom chatroom = new Chatroom();
			chatroom.setName("Justin Bieber");
			chatroom.setDescription("Is it a boy or a woman?");
			chatroomService.save(chatroom);
		}
		System.out.println("*** FINISH INIT DATABASE ***");
	}
}
