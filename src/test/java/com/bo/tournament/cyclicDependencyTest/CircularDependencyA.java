package com.bo.tournament.cyclicDependencyTest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyA {
	
	public CircularDependencyA(){
		System.out.println("A object is getting created" + circB);
	}
	
	@Autowired
    private CircularDependencyB circB;
 
	@PostConstruct
	public void initCircleB(){
		circB.setCircA(this);
	}
    
    public void setCircB(CircularDependencyB circB) {
        this.circB = circB;
    }
 
    public CircularDependencyB getCircB() {
        return circB;
    }
}