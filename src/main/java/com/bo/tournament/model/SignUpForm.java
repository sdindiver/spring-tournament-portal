package com.bo.tournament.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.bo.tournament.contraint.Confirm;

@Confirm(field="password", message="password does not matches")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignUpForm {
	@NotEmpty
	@JsonProperty("user_username")
	private String userName;
	@NotEmpty
	@JsonProperty("user_password")
	private String password;
	@NotEmpty
	@JsonProperty("user_cnf_password")
	private String confirmPassword;
	

	public SignUpForm(){
		System.out.println("indiver");
	}
	public SignUpForm(String userName, String password, String confirmPassword) {
		super();
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

}
