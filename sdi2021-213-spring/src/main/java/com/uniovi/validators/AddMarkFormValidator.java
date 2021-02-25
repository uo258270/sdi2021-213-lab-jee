package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;

@Component
public class AddMarkFormValidator implements Validator {

	@Autowired
	private MarksService marksService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Mark mark = (Mark) target;

		ValidationUtils.rejectIfEmpty(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmpty(errors, "score", "Error.empty");

		if (mark.getDescription().length() < 20) {
			errors.rejectValue("description", "Error.mark.description");
		}
		if (mark.getScore() > 10 || mark.getScore() < 0) {
			errors.rejectValue("score", "Error.mark.score");
		}
	}

}