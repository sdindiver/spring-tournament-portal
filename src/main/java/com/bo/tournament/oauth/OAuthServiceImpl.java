package com.bo.tournament.oauth;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bo.tournament.config.RepositoryFactory;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

@Service
public class OAuthServiceImpl implements OAuthService{
	
	@Inject
	RepositoryFactory factory;
	@Override
	public TournamentUserMaster getUserDetail(String username) {
		return factory.getRepository().getUserDetail(username);
	}

}
