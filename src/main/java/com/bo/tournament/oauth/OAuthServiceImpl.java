package com.bo.tournament.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.tournament.dao.UserMgmtDao;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

@Service
public class OAuthServiceImpl implements OAuthService{
	
	@Autowired
	UserMgmtDao userDao;
	@Override
	public TournamentUserMaster getUserDetail(String username) {
		return userDao.getUserDetail(username);
	}

}
