package com.bo.tournament.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.bo.tournament.contraint.UserManagmentBindingInitializer;
import com.bo.tournament.filters.ResourceAccessSecurityInterceptor;
import com.bo.tournament.resolver.GlobalArgumentResolver.AccountArgumentResolver;
import com.bo.tournament.resolver.GlobalArgumentResolver.HttpWrapperArgumentResolver;
import com.bo.tournament.versioned.ApiVersionRequestMappingHandlerMapping;
import com.bo.tournament.versioned.ApiVersionResponseHeaderModifierAdvice;
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new AccountArgumentResolver());
		argumentResolvers.add(new HttpWrapperArgumentResolver());
	}

	@Override
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/**");
	}
	
//	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		PathMatchConfigurer configurer = new PathMatchConfigurer();
		configurePathMatch(configurer);
		RequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());
		if(configurer.isUseSuffixPatternMatch() != null) {
			handlerMapping.setUseSuffixPatternMatch(configurer.isUseSuffixPatternMatch());
		}
		if(configurer.isUseRegisteredSuffixPatternMatch() != null) {
			handlerMapping.setUseRegisteredSuffixPatternMatch(configurer.isUseRegisteredSuffixPatternMatch());
		}
		if(configurer.isUseTrailingSlashMatch() != null) {
			handlerMapping.setUseTrailingSlashMatch(configurer.isUseTrailingSlashMatch());
		}
		if(configurer.getPathMatcher() != null) {
			handlerMapping.setPathMatcher(configurer.getPathMatcher());
		}
		if(configurer.getUrlPathHelper() != null) {
			handlerMapping.setUrlPathHelper(configurer.getUrlPathHelper());
		}
		return handlerMapping;
	}
	
	

	@Override
	public void addInterceptors(InterceptorRegistry interceptors) {
		interceptors.addInterceptor(new ResourceAccessSecurityInterceptor());
		interceptors.addInterceptor(new ApiVersionResponseHeaderModifierAdvice());

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
	
	protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
		return new UserManagmentBindingInitializer();
	}

}
