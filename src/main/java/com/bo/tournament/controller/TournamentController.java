package com.bo.tournament.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bo.tournament.annotations.ApiVersion;
import com.bo.tournament.annotations.ApiVersionLatest;
import com.bo.tournament.annotations.ApiVersionSupported;
import com.bo.tournament.annotations.Authenticated;
import com.bo.tournament.annotations.AuthenticationType;
import com.bo.tournament.exception.GlobalExceptionHandler;
import com.bo.tournament.hibernate.mapping.Tournament;
import com.bo.tournament.model.HttpWrapper;
import com.bo.tournament.service.TournamentMgmtService;

@RestController
@RequestMapping(value = "/")
public class TournamentController {

	
	TournamentMgmtService tournamentService;
	@Inject
	public TournamentController(TournamentMgmtService tournamentService) {
		this.tournamentService=tournamentService;
	}

	
	
	@ApiVersion(value="1.0.0")
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	//@RolesAllowed(value="isAuthenticated") -- Can be activated for doing with Aspects
	@Authenticated(type=AuthenticationType.STATELESS)
	public List<Tournament> getTournaments(HttpWrapper wrapper) throws InterruptedException {
		List<Tournament> tournamentList = tournamentService.getTournaments();
		return tournamentList;
	}
	
	@ApiVersion({"1.0.1", "1.0.2"})
	@ApiVersionSupported
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	@Authenticated(type=AuthenticationType.STATELESS)
	public List<Tournament> getVersionedTournaments(HttpWrapper wrapper) throws InterruptedException {
		List<Tournament> tournamentList = tournamentService.getTournaments();
		return tournamentList;
	}
	
	@ApiVersion("1.0.3")
    @ApiVersionLatest
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	@Authenticated(type=AuthenticationType.STATELESS)
	public List<Tournament> getVersionedLatestTournaments(HttpWrapper wrapper) throws InterruptedException {
		List<Tournament> tournamentList = tournamentService.getTournaments();
		return tournamentList;
	}
	

	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET, produces = "application/json")
	@Authenticated(type=AuthenticationType.STATEFULL)
	public Tournament getTournament(@PathVariable("id") int tournamentId,HttpWrapper wrapper) {
		Tournament tournament = tournamentService.getTournament(tournamentId);
		return tournament;

	}

	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTournament(@PathVariable("id") int tournamentId){
		throw new GlobalExceptionHandler.ResourceNotFoundException();
		//return tournamentService.deleteTournament(tournamentId);
	}

	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.PUT)
	public boolean updateTournament(Tournament tournament) {
		tournamentService.updateTournament(tournament);
		return true;
	}

	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.POST, consumes = "application/json")
	public boolean saveTournament(@Valid Tournament tournamentInfo, BindingResult result) {
		if (result.hasErrors()) {
			ObjectError error = new ObjectError("tournament.creation.failed", "Tournament are invalid");
			result.addError(error);
			return false;
		}
		tournamentService.saveTournament(tournamentInfo);
		return true;
	}

}
