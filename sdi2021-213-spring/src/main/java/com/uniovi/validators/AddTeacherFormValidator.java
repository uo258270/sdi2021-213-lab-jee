package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.TeachersService;

@Component
public class AddTeacherFormValidator implements Validator {
	@Autowired
	private TeachersService teachersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "Error.empty");


		if (teacher.getDni().length() != 9 || !Character.isAlphabetic(teacher.getDni().charAt(8))) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		if (teachersService.getTeacherByDni(teacher.getDni())) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (teacher.getNombre().length() < 5 || teacher.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (teacher.getApellidos().length() < 5 || teacher.getApellidos().length() > 24) {
			errors.rejectValue("apellidos", "Error.signup.lastName.length");
		}
		if (teacher.getCategoria().length() < 5 || teacher.getCategoria().length() > 24) {
			errors.rejectValue("categoria", "Error.signup.lastName.length");
		}
	}

}