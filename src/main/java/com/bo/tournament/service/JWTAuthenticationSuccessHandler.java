package com.bo.tournament.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.authentication.jwt.JWTTokenManager;
import com.bo.tournament.model.Account;

@Component(value = "JWTAuthenticationSuccessHandler")
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	public static final String AUTHORIZATION_HEADER_KEY = "AUTHORIZATION";

	@Override
	public void onAuthenticationSuccess(Account user, NativeWebRequest request) {

		String compactJws = JWTTokenManager.generateToken(user);

		HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
		Cookie cookie = new Cookie(AUTHORIZATION_HEADER_KEY, compactJws);
		response.addCookie(cookie);
	}

}
