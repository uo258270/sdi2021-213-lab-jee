package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

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
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddMarkFormValidator;

@Controller
public class MarksControllers {

	@Autowired
	private HttpSession httpSession;

	@Autowired // inyectar el servicio
	private MarksService marksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddMarkFormValidator addMarkFormValidator;

	@RequestMapping("/mark/list")
	public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		
		String dni = principal.getName();
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		if(searchText != null && !searchText.isEmpty()) {
			marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user);
			
		}else {
			marks = marksService.getMarksForUser(pageable, user);
		}
		
		model.addAttribute("marksList", marks.getContent());
		model.addAttribute("page", marks);
		return "mark/list";
	}

	@RequestMapping(value = "/mark/add")
	public String getMark(Model model) {
		model.addAttribute("mark", new Mark());
		return "mark/add";
	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@Validated Mark mark, BindingResult result) {
		addMarkFormValidator.validate(mark, result);
		if (result.hasErrors()) {
			return "mark/add";
		}
		marksService.addMark(mark);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		return "mark/details";
	}

	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/edit";
	}

	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(@Validated Mark mark, BindingResult result) {
		addMarkFormValidator.validate(mark, result);
		if (result.hasErrors()) {
			return "mark/edit";
		}
		marksService.addMark(mark);
		return "redirect:/mark/list/";
	}

	@RequestMapping("/mark/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = marksService.getMarksForUser(pageable, user);
		model.addAttribute("marksList", marks.getContent());
		return "mark/list :: tableMarks";
	}

	@RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id) {
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id) {
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
	}
}
