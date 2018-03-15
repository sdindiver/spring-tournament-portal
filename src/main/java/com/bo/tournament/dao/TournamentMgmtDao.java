package com.bo.tournament.dao;

import java.util.List;

import com.bo.tournament.hibernate.mapping.Tournament;


public interface TournamentMgmtDao {

	List<Tournament> getTournaments();

	Tournament getTournament(int tournamentId);

	boolean deleteTournament(int tournamentId);

	void updateTournament(Tournament tournament);

	void saveTournament(Tournament tournament);



}
