package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddUserFormValidator;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;

	@Autowired
	private AddUserFormValidator addUserFormValidator;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private MarksService marksService;

	@RequestMapping("/user/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNameOrLastname(pageable, searchText);
		} else {
			users = usersService.getUsers(pageable);
		}
		model.addAttribute("usersList", users);
		model.addAttribute("page", users);
		return "user/list";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String getUser(Model model) {
		model.addAttribute("rolesList", rolesService.getRoles());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@Validated User user, BindingResult result) {
		addUserFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "user/add";
		}
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(@Validated User user, BindingResult result) {
		User original = usersService.getUser(user.getId());
		original.setName(user.getName());
		original.setLastName(user.getLastName());
		addUserFormValidator.validate(original, result);
		if (result.hasErrors()) {
			return "user/add";
		}
		usersService.addUser(original);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("rolesList", rolesService.getRoles());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();
		User activeUser = usersService.getUserByDni(dni);
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		
		marks = marksService.getMarksForUser(pageable, activeUser);
		
		model.addAttribute("markList", marks.getContent());
		model.addAttribute("page", marks);
		
		
		return "home";
		
	}

	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "user/list :: tableUsers";
	}
}
