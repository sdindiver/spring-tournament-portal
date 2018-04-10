package com.bo.tournament.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bo.tournament.config.RepositoryFactory;
import com.bo.tournament.dao.UserMgmtDao;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;
import com.bo.tournament.model.SignUpForm;
@Service
public class UserManagmentServiceImpl implements UserManagementService{

	private UserMgmtDao userMgmtDao;
	@Inject
	public UserManagmentServiceImpl(RepositoryFactory daoFactory) {
		this.userMgmtDao = daoFactory.getRepository();
	}
	
	
	@Override
	public void registerUser(SignUpForm form) throws DuplicateUserNameException {
		TournamentUserMaster userDetails = userMgmtDao.getUserDetail(form.getUserName());
		if(userDetails != null){
			throw new DuplicateUserNameException("Duplicate User Name");
		}
		if(userDetails == null){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			TournamentUserMaster userMaster = new TournamentUserMaster(form.getUserName(), passwordEncoder.encode(form.getPassword()));
			userMgmtDao.saveUserDetail(userMaster);
		}
		
	}

}
