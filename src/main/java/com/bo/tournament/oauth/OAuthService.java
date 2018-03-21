package com.bo.tournament.oauth;

import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

public interface OAuthService {

	public TournamentUserMaster getUserDetail(String username);
}
