package cz.jiripinkas.example.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.jiripinkas.example.chat.service.UserService;

@Controller
public class UsersController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String list(Model model) {
		model.addAttribute("list", userService.findAll());
		return "users";
	}
}
