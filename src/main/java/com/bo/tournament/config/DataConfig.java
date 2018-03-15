package com.bo.tournament.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class DataConfig {
	
	
	@Configuration
	@Profile("staging")
	static class Staging{
		@Inject
		private Environment environment;
		@Bean
		public DataSource dataSource() {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUrl(environment.getProperty("database.url"));
			dataSource.setUser(environment.getProperty("database.username"));
			dataSource.setPassword(environment.getProperty("database.password"));
			return dataSource;
		}

		@Bean
		public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(factory);
			return transactionManager;
		}

		@Bean
		LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
			LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource);
			entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
			entityManagerFactoryBean.setPackagesToScan("com.bo.tournament");

			Properties jpaProperties = new Properties();
			jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
			jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
			jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
			entityManagerFactoryBean.setJpaProperties(jpaProperties);

			return entityManagerFactoryBean;
		}
	}
	
	@Configuration
	@Profile("development")
	static class Development{
		private Environment environment;
		
		@Inject
		public Development( Environment environment) {
			this.environment = environment;
		}
		
		@Bean(name="dataSource")
		public DataSource hibernateDataSource() {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUrl(environment.getProperty("database.url"));
			dataSource.setUser(environment.getProperty("database.username"));
			dataSource.setPassword(environment.getProperty("database.password"));
			return dataSource;
		}
		
		@Bean(name="sessionFactory")
		public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource){
			LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource);
			sessionFactoryBean.setPackagesToScan("com.bo.tournament");
			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
			hibernateProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
			hibernateProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

			sessionFactoryBean.setHibernateProperties(hibernateProperties);
			return sessionFactoryBean;
		}
	}
	
}
