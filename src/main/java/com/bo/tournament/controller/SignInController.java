package com.bo.tournament.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.exception.AuthenticationException;
import com.bo.tournament.model.SignInForm;
import com.bo.tournament.service.AuthenticationService;

@Controller
public class SignInController {


	private AuthenticationService signInService;
	@Inject	
	public SignInController(AuthenticationService signInService) {
		this.signInService = signInService;
	}
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn() throws IOException {
		return "redirect:/";
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signIn(@Valid SignInForm form, BindingResult result, final NativeWebRequest request){
		if (result.hasErrors()) {
			return "redirect:/signIn?error=1";
		}
		try{
			signInService.authenticate(form,request);
		}catch(AuthenticationException exception){
			return "redirect:/signIn?error=1";
		}
		return "redirect:/";
		

	}

}
