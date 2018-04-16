package com.bo.tournament.controller;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionInvalidator {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@RequestMapping(value="/invalidate")
	public void invalidateSession(){
		evict2ndLevelCache();
	}
	
	public void evict2ndLevelCache() {
	    try {
	        Map<String, ClassMetadata> classesMetadata = sessionFactory.getAllClassMetadata();
	        for (String entityName : classesMetadata.keySet()) {
	            sessionFactory.evictEntity(entityName);
	        }
	        System.out.println("Invalidating all second level cache");
	    } catch (Exception e) {
	    }
	}

}
