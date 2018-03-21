package com.bo.tournament.versioned;

import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils.MethodFilter;
import org.springframework.web.method.HandlerMethodSelector;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	private final ApiVersionAnnotationParser apiVersionAnnotationParser = new ApiVersionAnnotationParser();

	@Override
	/*
	 * Before modify this method was copy-pasted from {@link
	 * AbstractHandlerMethodMapping} Look for handler methods in a handler.
	 * 
	 * @param handler the bean name of a handler or a handler instance
	 */
	protected void detectHandlerMethods(final Object handler) {
		Class<?> handlerType = (handler instanceof String ? getApplicationContext().getType((String) handler)
				: handler.getClass());

		// Avoid repeated calls to getMappingForMethod which would rebuild
		// RequestMappingInfo instances
		final Map<Method, RequestMappingInfo> methodsAndMappings = new IdentityHashMap<Method, RequestMappingInfo>();
		final Class<?> userType = ClassUtils.getUserClass(handlerType);

		Set<Method> methods = HandlerMethodSelector.selectMethods(userType, new MethodFilter() {
			@Override
			public boolean matches(Method method) {
				RequestMappingInfo mapping = getMappingForMethod(method, userType);
				if (mapping != null) {
					methodsAndMappings.put(method, mapping);
					return true;
				}
				return false;
			}
		});
		Map<Method, List<RequestMappingInfo>> versionedMethods = apiVersionAnnotationParser
				.parseApiVersioningAnnotations(methodsAndMappings);
		logRequestMappingInfoCandidates(userType, methodsAndMappings);
		if (versionedMethods.isEmpty()) {
			methods.forEach((x)->{
				registerHandlerMethod(handler, x, methodsAndMappings.get(x));
			});
		} else {
			//customizedRegistrationProcess(handler, userType, versionedMethods);
			
			for(Map.Entry<Method, List<RequestMappingInfo>> entrySet : versionedMethods.entrySet()){
				for(RequestMappingInfo info: entrySet.getValue()){
					registerHandlerMethod(handler, entrySet.getKey(), info);	
				}
			}
//			versionedMethods.forEach((method, mappingList)->{
//				mappingList.forEach(mappingInfo->{
//					registerHandlerMethod(handler, method, mappingInfo);	
//				});
//				
//			});
		}
	}



	private void logRequestMappingInfoCandidates(final Class<?> userType, Map<Method, ?> methods) {
		if (logger.isDebugEnabled()) {
			logger.debug(methods.size() + " request handler methods found on " + userType + ": " + methods);
		}
	}

}
