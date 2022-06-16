package com.example.catering.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.catering.model.Ingrediente;
import com.example.catering.service.IngredienteService;

@Component
public class IngredienteValidator   implements Validator{

	@Autowired
	private IngredienteService ingredienteService;
	
    private static final Logger logger = LoggerFactory.getLogger(ChefValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origine", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.ingredienteService.alreadyExists((Ingrediente)o)) {
				 errors.rejectValue("nome", "duplicate");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Ingrediente.class.equals(aClass);
	}

}
