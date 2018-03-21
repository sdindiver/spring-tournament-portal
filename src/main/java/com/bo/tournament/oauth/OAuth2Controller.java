package com.bo.tournament.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

public class OAuth2Controller {
	
	@Autowired
	OAuthService userDao;
	
	@RequestMapping(value="/api/user", method = RequestMethod.GET, produces = "application/json")
	public TournamentUserMaster getUserInfo(){
		return userDao.getUserDetail("ram");
		
	}
	
	
	
	

}
