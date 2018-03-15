package com.bo.tournament.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpWrapper {

	private final HttpServletRequest request;
	private final HttpServletResponse response;

	public HttpWrapper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	public HttpSession getSession(){
		return getSession(false);
	}
	
	public HttpSession getSession(boolean doCreate){
		if(request== null){
			return null;
		}
		return request.getSession(doCreate);
	}

	public static HttpWrapper of(final HttpServletRequest request, final HttpServletResponse response) {
		return new HttpWrapper(request, response);
	}

}
