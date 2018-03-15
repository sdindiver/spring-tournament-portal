package com.bo.tournament.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	public LoggingAspect(){
		System.out.println("LoggingAspect Bean getting created");
	}

	@Pointcut("within(com.bo.tournament..*)")
	public void loggingPointcut() {
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint
	 *            join point for advice
	 * @param e
	 *            exception
	 */
	@AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("Exception handled" + joinPoint.getSignature().getName());
	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint
	 *            join point for advice
	 * @return result
	 * @throws Throwable
	 *             throws IllegalArgumentException
	 */
	@Around("loggingPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Error handled" + joinPoint.getSignature().getName());
		try {
			Object result = joinPoint.proceed();
			System.out.println("Error handled" + joinPoint.getSignature().getName());
			return result;
		} catch (IllegalArgumentException e) {
			System.out.println("Error handled" + joinPoint.getSignature().getName());
			throw e;
		}
	}
}