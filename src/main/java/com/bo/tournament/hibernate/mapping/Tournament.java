package com.bo.tournament.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bo_tournament_master")
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int tournamemntId;
	@Column(name = "name")
	private String tournamentName;

	public Tournament() {

	}

	public Tournament(int tournamentId, String tournamentName) {
		this.tournamemntId = tournamentId;
		this.tournamentName = tournamentName;

	}

	public void setTournamemntId(int tournamemntId) {
		this.tournamemntId = tournamemntId;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public int getTournamemntId() {
		return tournamemntId;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public static Tournament of(int tournamentId, String tournamentName) {
		return new Tournament(tournamentId, tournamentName);
	}

	public static Tournament of(int tournamentId) {
		return of(tournamentId, null);

	}

}
