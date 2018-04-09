package com.bo.tournament.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bo.tournament.hibernate.mapping.Tournament;

@RestController
@RequestMapping(value = "/")
public class WeatherController {

	
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ResponseEntity<String> saveTournament(@Valid Tournament tournamentInfo, BindingResult result) throws URISyntaxException {
		
		RestTemplate template = new RestTemplate();
		String weatherAPIUrl = System.getenv("WEATHER_API");
		
		if(weatherAPIUrl !=null && !weatherAPIUrl.isEmpty()){
			URI weatherURI = new URI(weatherAPIUrl);
			return template.getForEntity(weatherURI, String.class);
		}
		return (ResponseEntity<String>) ResponseEntity.EMPTY;
		
	}

}
