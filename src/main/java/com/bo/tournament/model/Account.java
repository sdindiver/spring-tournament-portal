package com.bo.tournament.model;

public class Account {

	private String userName;
	
	private Account(String name){
		this.userName=name;
	}
	public static Account Of(String userName){
		return new Account(userName);
	}
	
	
	public String getUserName(){
		return this.userName;
	}
	
	
}
