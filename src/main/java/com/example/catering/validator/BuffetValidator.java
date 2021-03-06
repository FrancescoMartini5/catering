package com.example.catering.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.catering.model.Buffet;
import com.example.catering.service.BuffetService;



@Component
public class BuffetValidator implements Validator {
	@Autowired
	private BuffetService buffetService;
	
    private static final Logger logger = LoggerFactory.getLogger(BuffetValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.buffetService.alreadyExists((Buffet)o)) {
				 errors.rejectValue("nome", "duplicate");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Buffet.class.equals(aClass);
	}
}
