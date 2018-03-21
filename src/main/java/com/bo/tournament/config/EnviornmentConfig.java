package com.bo.tournament.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
public class EnviornmentConfig {

	/**
	 * Properties to support the 'embedded' mode of operation.
	 */
	@Configuration
	@Profile("development")
	@PropertySource("classpath:development.properties")
	static class Development {
		static{
			System.out.println("properties are loading");
		}
	}
	
	@Configuration
	@Profile("staging")
	@PropertySource("classpath:staging.properties")
	static class Staging {
		static{
			System.out.println("properties are loading");
		}
	}

}