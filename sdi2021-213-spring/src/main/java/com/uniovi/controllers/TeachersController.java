package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;

@RestController
public class TeachersController {

	@Autowired //Inyectar el servicio
	private TeachersService teachersService;

	@RequestMapping("/teacher/list")
	public String getList() {
		return teachersService.getTeachers().toString();
	}

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Teacher teacher) {
		teachersService.addTeacher(teacher);
		return "Ok";
	}
	
	@RequestMapping(value = "/teacher/add")
	public String getTeacher() {
		return "teacher/add";
	}

	@RequestMapping("/teacher/details/{dni}")
	public String getDetail(@PathVariable Long dni) {
		return teachersService.getTeacher(dni).toString();
	}

	@RequestMapping("/teacher/delete/{dni}")
	public String deleteTeacher(@PathVariable Long dni) {
		teachersService.deleteTeacher(dni);
		return "Ok";
	}

	@RequestMapping(value = "/teacher/edit/{dni}", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Long dni, @ModelAttribute Teacher teacher) {
		Teacher t = teachersService.getTeacher(dni);
		t.setNombre(teacher.getNombre());
		t.setApellidos(teacher.getApellidos());
		t.setCategoria(teacher.getCategoria());
		return "Ok";
	}
	
	@RequestMapping(value = "/teacher/edit/{dni}")
	public String getEdit(Model model, @PathVariable Long dni) {
		model.addAttribute("teacher", teachersService.getTeacher(dni));
		return "teacher/edit";
	}

	@RequestMapping(value = "/teacher/edit", method = RequestMethod.POST)
	public String setEdit(Model model, @ModelAttribute Teacher teacher) {
		teachersService.addTeacher(teacher);
		return "redirect:/teacher/details/" + teacher.getDni();
	}
}
