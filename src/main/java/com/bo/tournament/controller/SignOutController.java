package com.bo.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.bo.tournament.authentication.jwt.JWTTokenManager;

@Controller
public class SignOutController {

	@RequestMapping(value="/signout")
	public String signOut(NativeWebRequest request){
		request.removeAttribute("principle", RequestAttributes.SCOPE_SESSION);
		JWTTokenManager.clearCookieJwt(request);
		return "redirect:/";
	}
}
