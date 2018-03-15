package com.bo.tournament.exception;

public class AccessDeniedException extends AccountException {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String message) {
		super(message);
	}

}
