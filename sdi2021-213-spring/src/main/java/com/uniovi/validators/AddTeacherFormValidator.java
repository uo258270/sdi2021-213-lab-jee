package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;

@Component
public class AddTeacherFormValidator implements Validator {
	@Autowired
	private MarksService teachersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;

		ValidationUtils.rejectIfEmpty(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmpty(errors, "apellidos", "Error.empty");
		ValidationUtils.rejectIfEmpty(errors, "categoria", "Error.empty");
	}

}