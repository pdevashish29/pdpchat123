package cz.jiripinkas.example.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("list", userService.findAll());
		return "users";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int id) {
		userService.delete(id);
		return "redirect:/users.html";
	}
	
	@ModelAttribute
	public User construct() {
		User user = new User();
		user.setName("jmeno");
		return user;
	}

	@RequestMapping("/add")
	public String showAdd() {
		return "user";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute User user) {
		userService.add(user);
		return "redirect:/users.html";
	}
}
