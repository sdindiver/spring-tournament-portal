package com.bo.tournament.service;

import com.bo.tournament.exception.AuthenticationException;

public interface AuthenticationFailureHandler {

	public void onAuthenticationFailure() throws AuthenticationException;
}