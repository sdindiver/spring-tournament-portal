package com.bo.tournament.service;

import com.bo.tournament.model.SignUpForm;

public interface UserManagementService {

	void registerUser(SignUpForm form) throws DuplicateUserNameException;

}
