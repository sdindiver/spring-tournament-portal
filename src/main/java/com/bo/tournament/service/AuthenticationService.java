package com.bo.tournament.service;

import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.exception.AuthenticationException;
import com.bo.tournament.model.SignInForm;

public interface AuthenticationService {

	void authenticate(SignInForm form,NativeWebRequest request) throws AuthenticationException;

}
