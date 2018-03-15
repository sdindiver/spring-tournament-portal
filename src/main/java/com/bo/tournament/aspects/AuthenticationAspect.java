package com.bo.tournament.aspects;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.bo.tournament.annotations.Authenticated;
import com.bo.tournament.annotations.AuthenticationType;
import com.bo.tournament.authentication.jwt.JWTTokenManager;
import com.bo.tournament.model.Account;
import com.bo.tournament.model.HttpWrapper;

@Aspect
@Component
public class AuthenticationAspect {

	public AuthenticationAspect() {
		System.out.println("AuthenticationAspect bean is created");
	}

	private HttpWrapper httpWrapper;

	private AuthenticationType authenticationType;

	@Around(value = "@annotation(com.bo.tournament.annotations.Authenticated)", argNames = "pjp")
	public void checkAuthenticationMethod(ProceedingJoinPoint pjp) throws Throwable {
		checkAuthentication(pjp);
	}

	private void checkAuthentication(ProceedingJoinPoint jointPoint) throws Throwable {
		HttpWrapper wrapper = null;
		if (jointPoint.getArgs().length > 0) {
			wrapper = (HttpWrapper) jointPoint.getArgs()[jointPoint.getArgs().length - 1];
		}

		setAuthenticationType(jointPoint);
		setHttpWrapper(wrapper);
		if (getUser() != null) {
			jointPoint.proceed();
		} else {
			getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	private void setAuthenticationType(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Authenticated authenticationAnnotation = method.getAnnotation(Authenticated.class);
		setAuthenticationType(authenticationAnnotation.type());
	}

	private void setHttpWrapper(HttpWrapper httpWrapper) {
		this.httpWrapper = httpWrapper;

	}

	public HttpWrapper getHttpWrapper() {
		return this.httpWrapper;
	}

	private Account getUser() {
		boolean isStateFullAuthentication = getAuthenticationType().equals(AuthenticationType.STATEFULL);
		boolean isStateLessAuthentication = getAuthenticationType().equals(AuthenticationType.STATELESS);
		if (isStateFullAuthentication && getSession() != null && getSession().getAttribute("principle") != null
				&& getSession().getAttribute("principle") instanceof Account) {
			return (Account) getSession().getAttribute("principle");
		}

		if (isStateLessAuthentication) {
			return JWTTokenManager.getUserByJwtToken(getRequest(),getResponse(), (x,y)-> {
				return Account.Of(x.getUserName());
			});
			
		}
		return null;
	}

	private HttpSession getSession() {
		return getHttpWrapper().getSession();
	}

	private HttpServletResponse getResponse() {
		return getHttpWrapper().getResponse();
	}

	private HttpServletRequest getRequest() {
		return getHttpWrapper().getRequest();
	}

	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}

}
