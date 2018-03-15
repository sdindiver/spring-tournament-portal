package com.bo.tournament.service;

import java.util.List;

import com.bo.tournament.hibernate.mapping.Tournament;


public interface TournamentMgmtService {

	List<Tournament> getTournaments();

	Tournament getTournament(int tournamentId);

	boolean deleteTournament(int tournamentId);

	void updateTournament(Tournament tournament);

	void saveTournament(Tournament tournament);

}
