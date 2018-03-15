package com.bo.tournament.service;

import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.model.Account;

public interface AuthenticationSuccessHandler {
	public void onAuthenticationSuccess(Account user, NativeWebRequest request);
}
