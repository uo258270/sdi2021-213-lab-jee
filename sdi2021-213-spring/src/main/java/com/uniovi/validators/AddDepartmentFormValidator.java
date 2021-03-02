package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Department;
import com.uniovi.entities.User;
import com.uniovi.services.DepartmentsService;

@Component
public class AddDepartmentFormValidator implements Validator {

	@Autowired
	private DepartmentsService departmentsService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Department department = (Department) target;

		ValidationUtils.rejectIfEmpty(errors, "description", "Error.empty");
	}

}