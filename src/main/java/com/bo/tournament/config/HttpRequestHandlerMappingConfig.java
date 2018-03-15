package com.bo.tournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bo.tournament.controller.BeanNameUrlMappingTestControllers;

@Configuration
public class HttpRequestHandlerMappingConfig {

	
	@Bean(name="/byControllerAdaptor")
	public BeanNameUrlMappingTestControllers.ByControllerAdaptor controllerAdaptorUrlMapping(){
		return new BeanNameUrlMappingTestControllers.ByControllerAdaptor();
	}
	
	@Bean(name="/byHttpRequestHandlerAdaptor")
	public BeanNameUrlMappingTestControllers.ByHttpRequestHandlerAdaptor httpRequestHandlerAdaprotUrlMapping(){
		return new BeanNameUrlMappingTestControllers.ByHttpRequestHandlerAdaptor();
	}
}
