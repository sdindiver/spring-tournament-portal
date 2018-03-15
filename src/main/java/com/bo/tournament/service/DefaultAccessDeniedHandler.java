package com.bo.tournament.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bo.tournament.exception.AccessDeniedException;

@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void onAccessDenied(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (IOException e) {
			new AccessDeniedException("Access is denied");
		}
	}

}
