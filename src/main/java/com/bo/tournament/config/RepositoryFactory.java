package com.bo.tournament.config;

import javax.inject.Inject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bo.tournament.dao.UserMgmtDao;

@Component
public class RepositoryFactory{
	@Inject
	private ApplicationContext ctx;
	String name;
	public  UserMgmtDao getRepository() throws BeansException{
		try{
			return (UserMgmtDao)ctx.getBean(Class.forName(System.getenv("USER_DAO")));
		}catch(Exception e){
			throw new RuntimeException("Class not found Exception");
		}		
	}

}
