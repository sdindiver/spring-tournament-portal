package com.bo.tournament.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SignInForm {
	
	@NotEmpty
	@NotBlank
	@JsonProperty("user_username")
	private String userName;
	@NotEmpty
	@NotBlank
	@JsonProperty("user_password")
	private String password;
	
	public SignInForm(){
		
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SignInForm(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	

	
}
