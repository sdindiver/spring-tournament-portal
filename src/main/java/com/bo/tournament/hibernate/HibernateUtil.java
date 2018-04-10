package com.bo.tournament.hibernate;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bo.tournament.dao.UserMgmtDao;
import com.bo.tournament.dao.UserMgmtDaoImpl;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static ThreadLocal<Session> sessionLocal = new ThreadLocal<>();

	static {
		try {
			//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

			Configuration configuration = new Configuration();
			configuration.configure("com/bo/tournament/hibernate/hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Session Factory could not be created." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//Usecase over here: not necessary to pass session through method call 
	public static Session getSession(){
		sessionLocal.set(sessionFactory.openSession());
		return sessionLocal.get();
	}
	
	public static void closeSession(){
		Session session = sessionLocal.get();
		session.close();
	}
	
	

}