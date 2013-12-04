package cz.jiripinkas.example.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	// public List<User> findByUserName() {
	// }

	public long count() {
		return userRepository.count();
	}
}
