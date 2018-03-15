package com.bo.tournament.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bo.tournament.authentication.jwt.JWTToken;
import com.bo.tournament.model.Account;
import com.bo.tournament.service.JWTAuthenticationSuccessHandler;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET,headers="!" + JWTAuthenticationSuccessHandler.AUTHORIZATION_HEADER_KEY)
	public String home(Account account) throws IOException {
		System.out.println(Thread.currentThread().getName());
		if(account == null){
			return "index";
		}
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET,headers=JWTAuthenticationSuccessHandler.AUTHORIZATION_HEADER_KEY)
	public String home(JWTToken token) throws IOException {
		System.out.println(Thread.currentThread().getName());
		if(token == null){
			return "index";
		}
		return "home";
	}

}
