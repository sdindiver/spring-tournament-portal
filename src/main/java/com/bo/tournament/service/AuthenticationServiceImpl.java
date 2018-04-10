package com.bo.tournament.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.config.RepositoryFactory;
import com.bo.tournament.dao.UserMgmtDao;
import com.bo.tournament.exception.AuthenticationException;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;
import com.bo.tournament.model.Account;
import com.bo.tournament.model.SignInForm;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private UserMgmtDao userMgmtDao;
	private AuthenticationSuccessHandler successHandler;
	private AuthenticationFailureHandler failureHandler;

	@Inject
	public AuthenticationServiceImpl(RepositoryFactory daoFactory,
			@Qualifier(value = "JWTAuthenticationSuccessHandler") AuthenticationSuccessHandler successHandler,
			AuthenticationFailureHandler failureHandler) {
		this.userMgmtDao = daoFactory.getRepository();
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
	}

	@Override
	public void authenticate(SignInForm form, NativeWebRequest request) throws AuthenticationException {
		TournamentUserMaster userDetails = userMgmtDao.getUserDetail(form.getUserName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		if (userDetails == null || !encoder.matches(form.getPassword(), userDetails.getPassword())) {
			failureHandler.onAuthenticationFailure();
		}
		Account user = Account.Of(userDetails.getUsername());
		successHandler.onAuthenticationSuccess(user, request);

	}

}
