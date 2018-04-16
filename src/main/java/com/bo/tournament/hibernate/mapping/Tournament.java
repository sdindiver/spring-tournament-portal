package com.bo.tournament.hibernate.mapping;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "bo_tournament_master")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("tournament-id")
	private int tournamentId;
	@Column(name = "name")
	@JsonProperty("tournament-name")
	private String tournamentName;

	public Tournament() {

	}

	public Tournament(int tournamentId, String tournamentName) {
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;

	}

	public void setTournamemntId(int tournamemntId) {
		this.tournamentId = tournamemntId;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	public int getTournamentId() {
		return tournamentId;
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
