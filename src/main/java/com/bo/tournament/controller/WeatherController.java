package com.bo.tournament.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/")
public class WeatherController {

	
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ResponseEntity<String> getWeather() throws URISyntaxException {
		
		RestTemplate template = new RestTemplate();
		String weatherAPIUrl = System.getenv("WEATHER_API");
		
		if(weatherAPIUrl !=null && !weatherAPIUrl.isEmpty()){
			URI weatherURI = new URI(weatherAPIUrl);
			return template.getForEntity(weatherURI, String.class);
		}
		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return response;
		
	}

}
