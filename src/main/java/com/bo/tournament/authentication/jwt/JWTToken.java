package com.bo.tournament.authentication.jwt;

import java.time.LocalDateTime;

public class JWTToken {
	private String userName;
	private LocalDateTime expiryTime;

	private JWTToken(String userName, LocalDateTime expiryTime) {
		this.userName = userName;
		this.expiryTime = expiryTime;
	}

	public String getUserName() {
		return userName;
	}

	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	public static JWTToken of(String userName, LocalDateTime expiryTime) {
		return new JWTToken(userName, expiryTime);
	}
	public static JWTToken of(String userName, String expiryTime) {
		LocalDateTime dateTime = LocalDateTime.parse(expiryTime);
		return of(userName, dateTime);
	}

}
