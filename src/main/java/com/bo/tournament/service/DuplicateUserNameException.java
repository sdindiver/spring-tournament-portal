package com.bo.tournament.service;

import com.bo.tournament.exception.AccountException;

public class DuplicateUserNameException extends AccountException {

	public DuplicateUserNameException(String message) {
		super(message);

	}

	private static final long serialVersionUID = 1L;

}
