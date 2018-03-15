package com.bo.tournament.service;

import org.springframework.stereotype.Component;

import com.bo.tournament.exception.AuthenticationException;
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public DefaultAuthenticationFailureHandler(){
		System.out.println("Failure handler Bean creating");
	} 
	@Override
	public void onAuthenticationFailure() throws AuthenticationException  
	{
		
		throw new AuthenticationException("Invalid Credentials");
		
	}

}
