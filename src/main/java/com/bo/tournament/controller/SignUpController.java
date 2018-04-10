package com.bo.tournament.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bo.tournament.model.Account;
import com.bo.tournament.model.SignUpForm;
import com.bo.tournament.service.DuplicateUserNameException;
import com.bo.tournament.service.UserManagementService;

@Controller
public class SignUpController {

	
	UserManagementService userManagmentService;
	
	@Inject
	public SignUpController(UserManagementService userManagmentService) {
		this.userManagmentService = userManagmentService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid SignUpForm form, BindingResult result) throws DuplicateUserNameException {
		if (result.hasErrors() || form.getConfirmPassword().trim().isEmpty()
				|| form.getUserName().trim().isEmpty() || form.getPassword().trim().isEmpty()) {
			return "redirect:/register?error=1";
		}
		userManagmentService.registerUser(form);
		return "redirect:/register?success=1";
	}
	
	@ExceptionHandler(value=DuplicateUserNameException.class)
	public String handleDuplicateUser(){
		return "redirect:/register?error=2";
	}
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Account account) {
		if(account != null){
			return "redirect:/";
		}
		return "register";
	}
	
	
}
