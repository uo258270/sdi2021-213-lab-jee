package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Department;
import com.uniovi.services.DepartmentsService;
import com.uniovi.validators.AddDepartmentFormValidator;

@Controller
public class DepartmentsController {

	@Autowired
	private DepartmentsService departmentsService;

	@Autowired
	private AddDepartmentFormValidator addDepartmentFormValidator;

	@RequestMapping("/department/list")
	public String getList(Model model) {
		model.addAttribute("departmentsList", departmentsService.getDepartments());
		return "department/list";
	}

	@RequestMapping(value = "/department/add")
	public String getDepartment(Model model) {
		model.addAttribute("department", new Department());
		return "department/add";
	}

	@RequestMapping(value = "/department/add", method = RequestMethod.POST)
	public String setDepartment(@Validated Department department, BindingResult result) {
		addDepartmentFormValidator.validate(department, result);
		if(result.hasErrors()) {
			return "department/add";
		}
		departmentsService.addDepartment(department);
		return "redirect:/department/list";
	}

	@RequestMapping("/department/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("department", departmentsService.getDepartment(id));
		return "department/details";
	}

	@RequestMapping("/department/delete/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		departmentsService.deleteDepartment(id);
		return "redirect:/department/list";
	}

	@RequestMapping(value = "/department/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("department", departmentsService.getDepartment(id));
		return "department/edit";
	}

	@RequestMapping(value = "/department/edit/{id}", method = RequestMethod.POST)
	public String setEdit(@Validated Department department, BindingResult result) {
		addDepartmentFormValidator.validate(department, result);
		if(result.hasErrors()) {
			return "department/edit";
		}
		departmentsService.addDepartment(department);
		return "redirect:/department/list/";
	}
}