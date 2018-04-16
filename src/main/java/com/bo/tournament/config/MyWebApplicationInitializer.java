package com.bo.tournament.config;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class MyWebApplicationInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "log4j.properties";
    	PropertyConfigurator.configure(log4jConfigFile);
    	setActiveProfile(servletContext);
    }
    
    
    public void setActiveProfile(ServletContext servletContext){
    	if(System.getenv("spring.profiles.active") == null)
    		servletContext.setInitParameter("spring.profiles.active", "development");
    	if(System.getenv("spring.profiles.active") != null 
    			&& !System.getenv("spring.profiles.active").isEmpty()){
    		servletContext.setInitParameter("spring.profiles.active", System.getenv("spring.profiles.active"));
    	}
    }

}