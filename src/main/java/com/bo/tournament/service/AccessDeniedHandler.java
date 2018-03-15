package com.bo.tournament.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccessDeniedHandler {

	public void onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
