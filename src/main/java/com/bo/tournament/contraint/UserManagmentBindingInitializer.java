package com.bo.tournament.contraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class UserManagmentBindingInitializer extends ConfigurableWebBindingInitializer{
	@Autowired
	@Qualifier("passwordMatchvalidator")
	private Validator passwordMatchvalidator;
	
	
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setValidator(passwordMatchvalidator);
		
	}
	
	

}
