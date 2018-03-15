package com.bo.tournament.dao;

import com.bo.tournament.exception.DataAcessException;
import com.bo.tournament.hibernate.mapping.TournamentUserMaster;

public interface UserMgmtDao {

	public TournamentUserMaster getUserDetail(String Username) throws DataAcessException;

	public void saveUserDetail(TournamentUserMaster userMaster) throws DataAcessException;

}
