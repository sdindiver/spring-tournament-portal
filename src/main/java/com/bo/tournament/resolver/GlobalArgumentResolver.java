package com.bo.tournament.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bo.tournament.authentication.jwt.JWTTokenManager;
import com.bo.tournament.model.Account;
import com.bo.tournament.model.HttpWrapper;

public class GlobalArgumentResolver {

	public static class AccountArgumentResolver implements HandlerMethodArgumentResolver {

		public boolean supportsParameter(MethodParameter parameter) {
			return Account.class.isAssignableFrom(parameter.getParameterType());
		}

		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
				NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			
			Account account = JWTTokenManager.getUserByJwtToken(webRequest, (x,y)-> {
				return Account.Of(x.getUserName());
			} );
			return account!=null ? account: webRequest.getAttribute("principle", RequestAttributes.SCOPE_SESSION);
		}

	}

	public static class HttpWrapperArgumentResolver implements HandlerMethodArgumentResolver {

		public boolean supportsParameter(MethodParameter parameter) {
			return HttpWrapper.class.isAssignableFrom(parameter.getParameterType());
		}

		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
				NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
			return HttpWrapper.of(request, response);
		}

	}

}
