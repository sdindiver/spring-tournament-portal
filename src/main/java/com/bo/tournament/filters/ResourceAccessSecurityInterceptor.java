package com.bo.tournament.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bo.tournament.annotations.RolesAllowed;
import com.bo.tournament.model.Account;
import com.bo.tournament.service.AccessDeniedHandler;

public class ResourceAccessSecurityInterceptor extends HandlerInterceptorAdapter {

	private HttpServletRequest request;

	@Inject
	private AccessDeniedHandler accessDeniedHandler;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		setHttpRequest(request);
		if (handler instanceof HandlerMethod) {
			HandlerMethod resourceMethod = (HandlerMethod) handler;
			RolesAllowed rolesAllowedAnnotation = resourceMethod.getMethodAnnotation(RolesAllowed.class);
			if (rolesAllowedAnnotation != null) {
				List<String> allowedRoles = Arrays.asList(rolesAllowedAnnotation.value());
				for (String AllowedRole : allowedRoles) {
					boolean isAllowed = (Boolean) extracted(AllowedRole).invoke(this, null);
					if (!isAllowed) {
						accessDeniedHandler.onAccessDenied(request, response);
						return false;
					}
				}
			}
		}

		return super.preHandle(request, response, handler);
	}

	private Method extracted(String AllowedRole) throws NoSuchMethodException {
		return getClass().getMethod(AllowedRole, null);
	}

	public boolean isAuthenticated() throws IOException {
		if (getUser() != null)
			return true;
		return false;
	}

	public Account getUser() {
		if (getSession().getAttribute("principle") != null
				&& getSession().getAttribute("principle") instanceof Account) {
			return (Account) getSession().getAttribute("principle");
		}
		return null;
	}

	public HttpSession getSession() {
		return this.request.getSession();
	}

	public void setHttpRequest(HttpServletRequest request) {
		this.request = request;
	}

}
