package com.bo.tournament.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.bo.tournament.aspects.AuthenticationAspect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.bo.tournament")
public class AspectConfig {

	@Bean
	public AuthenticationAspect authenticationAspect() {
		//Arrays.asList(a)
		return new AuthenticationAspect();
	}
//
//	@Bean
//	public LoggingAspect loggingAspect() {
//		return new LoggingAspect();
//	}
}
