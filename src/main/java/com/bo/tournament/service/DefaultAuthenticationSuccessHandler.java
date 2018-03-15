package com.bo.tournament.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.bo.tournament.model.Account;

@Component(value="DefaultAuthenticationSuccessHandler")
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	public DefaultAuthenticationSuccessHandler() {
		System.out.println("Success handler Bean creating");
	}

	@Override
	public void onAuthenticationSuccess(Account account, NativeWebRequest request) {
		request.setAttribute("principle", account, RequestAttributes.SCOPE_SESSION);
	}

}
