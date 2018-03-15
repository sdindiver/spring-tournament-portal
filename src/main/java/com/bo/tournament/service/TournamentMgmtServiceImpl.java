package com.bo.tournament.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bo.tournament.dao.TournamentMgmtDao;
import com.bo.tournament.hibernate.mapping.Tournament;

@Service
public class TournamentMgmtServiceImpl implements TournamentMgmtService {

	
	TournamentMgmtDao tournamentDao;
	
	@Inject
	public TournamentMgmtServiceImpl(TournamentMgmtDao tournamentDao) {
		this.tournamentDao=tournamentDao;
	}

	@Override
	public List<Tournament> getTournaments() {
		return tournamentDao.getTournaments();
	}

	@Override
	public Tournament getTournament(int tournamentId) {
		return tournamentDao.getTournament(tournamentId);
	}

	@Override
	public boolean deleteTournament(int tournamentId) {
		return tournamentDao.deleteTournament(tournamentId);
	}

	@Override
	public void saveTournament(Tournament tournament) {
		tournamentDao.saveTournament(tournament);

	}

	@Override
	public void updateTournament(Tournament tournament) {
		tournamentDao.updateTournament(tournament);

	}

}
