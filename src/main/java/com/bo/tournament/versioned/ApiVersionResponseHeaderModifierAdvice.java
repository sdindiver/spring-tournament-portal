package com.bo.tournament.versioned;

import static com.bo.tournament.versioned.ApiVersionHeader.HEADER_NAME;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bo.tournament.annotations.ApiVersion;



public class ApiVersionResponseHeaderModifierAdvice extends HandlerInterceptorAdapter {

	private static final String NOT_VERSIONED = "not versioned";
	private final Map<Integer, String> methodVersions = new HashMap<>();

	private String scanApiVersionAnnotation(Method method) {
		
		ApiVersion apiVersion = method.getDeclaredAnnotation(ApiVersion.class);
		if (apiVersion != null) {
			return String.join(", ", apiVersion.value());

		}
		return NOT_VERSIONED;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod beanMethodHandler = (HandlerMethod) handler;
		String headerData = methodVersions.computeIfAbsent(beanMethodHandler.getMethod().hashCode(), key -> scanApiVersionAnnotation(beanMethodHandler.getMethod()));
		response.addHeader(HEADER_NAME, headerData);
		super.postHandle(request, response, handler, modelAndView);
	}
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HandlerMethod beanMethodHandler = (HandlerMethod) handler;
//		String headerData = methodVersions.computeIfAbsent(beanMethodHandler.getMethod().hashCode(), key -> scanApiVersionAnnotation(beanMethodHandler.getMethod()));
//		response.addHeader(HEADER_NAME, headerData);
////		 response.getHeaders().add(HEADER_NAME, methodVersions.computeIfAbsent(returnType.hashCode(), key -> scanApiVersionAnnotation(returnType)));
////	        return body;
//		super.afterCompletion(request, response, handler, ex);
//	}
}