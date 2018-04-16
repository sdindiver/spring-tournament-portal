package com.bo.tournament.cyclicDependencyTest;

import org.springframework.stereotype.Component;

@Component
public class CircularDependencyB {
	public CircularDependencyB(){
		System.out.println("B object is getting created" + circA);
	}
	
    private CircularDependencyA circA;
 
    private String message = "Hi!";
 
    
    public void setCircA(CircularDependencyA circA) {
        this.circA = circA;
    }
 
    public String getMessage() {
        return message;
    }
}